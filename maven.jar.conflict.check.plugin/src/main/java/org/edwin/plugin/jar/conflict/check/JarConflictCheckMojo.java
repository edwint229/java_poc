package org.edwin.plugin.jar.conflict.check;

import java.io.StringWriter;
import java.io.Writer;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.maven.artifact.Artifact;
import org.apache.maven.artifact.resolver.filter.ArtifactFilter;
import org.apache.maven.artifact.resolver.filter.ScopeArtifactFilter;
import org.apache.maven.plugin.AbstractMojo;
import org.apache.maven.plugin.MojoExecutionException;
import org.apache.maven.plugin.MojoFailureException;
import org.apache.maven.plugins.annotations.Component;
import org.apache.maven.plugins.annotations.Mojo;
import org.apache.maven.plugins.annotations.Parameter;
import org.apache.maven.plugins.annotations.ResolutionScope;
import org.apache.maven.project.MavenProject;
import org.apache.maven.shared.dependency.graph.DependencyGraphBuilder;
import org.apache.maven.shared.dependency.graph.DependencyNode;
import org.apache.maven.shared.dependency.graph.traversal.BuildingDependencyNodeVisitor;
import org.apache.maven.shared.dependency.graph.traversal.DependencyNodeVisitor;
import org.apache.maven.shared.dependency.graph.traversal.SerializingDependencyNodeVisitor;
import org.apache.maven.shared.dependency.graph.traversal.SerializingDependencyNodeVisitor.GraphTokens;
import org.apache.maven.shared.dependency.tree.DependencyTreeBuilder;
import org.edwin.plugin.dependency.tree.DOTDependencyNodeVisitor;
import org.edwin.plugin.dependency.tree.DependencyUtil;
import org.edwin.plugin.dependency.tree.GraphmlDependencyNodeVisitor;
import org.edwin.plugin.dependency.tree.TGFDependencyNodeVisitor;

/**
 * Check project jar dependency conflict
 * 
 * @author edwin
 */

@Mojo(name = "jar-conflict-check", requiresDependencyResolution = ResolutionScope.TEST, threadSafe = true)
public class JarConflictCheckMojo extends AbstractMojo {

    /**
     * The Maven project.
     */
    @Parameter(defaultValue = "${project}", readonly = true, required = true)
    private MavenProject project;

    /**
     * The dependency tree builder to use.
     */
    @Component(hint = "default")
    private DependencyGraphBuilder dependencyGraphBuilder;

    /**
     * The dependency tree builder to use for verbose output.
     */
    @Component
    private DependencyTreeBuilder dependencyTreeBuilder;

    /**
     * If specified, this parameter will cause the dependency tree to be written
     * using the specified format. Currently supported format are:
     * <code>text</code>, <code>dot</code>, <code>graphml</code> and
     * <code>tgf</code>.
     * <p/>
     * These formats can be plotted to image files. An example of how to plot a
     * dot file using pygraphviz can be found <a href=
     * "http://networkx.lanl.gov/pygraphviz/tutorial.html#layout-and-drawing"
     * >here</a>.
     * 
     * @since 2.2
     */
    @Parameter(property = "outputType", defaultValue = "text")
    private String outputType;

    /**
     * The scope to filter by when resolving the dependency tree, or
     * <code>null</code> to include dependencies from all scopes. Note that this
     * feature does not currently work due to MNG-3236.
     * 
     * @see <a href="http://jira.codehaus.org/browse/MNG-3236">MNG-3236</a>
     * @since 2.0-alpha-5
     */
    @Parameter(property = "scope")
    private String scope;

    /**
     * The token set name to use when outputting the dependency tree. Possible
     * values are <code>whitespace</code>, <code>standard</code> or
     * <code>extended</code>, which use whitespace, standard (ie ASCII) or
     * extended character sets respectively.
     * 
     * @since 2.0-alpha-6
     */
    @Parameter(property = "tokens", defaultValue = "standard")
    private String tokens;

    /**
     * The computed dependency tree root node of the Maven project.
     */
    private DependencyNode rootNode;

    /**
     * Whether to append outputs into the output file or overwrite it.
     * 
     * @since 2.2
     */
    @Parameter(property = "appendOutput", defaultValue = "false")
    private boolean appendOutput;

    /**
     * Skip plugin execution completely.
     * 
     * @since 2.7
     */
    @Parameter(property = "jar.conflict.check.skip", defaultValue = "false")
    private boolean skip;

    /**
     * Gets the Maven project used by this mojo.
     * 
     * @return the Maven project
     */
    public MavenProject getProject() {
        return project;
    }

    public boolean isSkip() {
        return skip;
    }

    public void setSkip(boolean skip) {
        this.skip = skip;
    }

    /**
     * Gets the computed dependency graph root node for the Maven project.
     * 
     * @return the dependency tree root node
     */
    public DependencyNode getDependencyGraph() {
        return rootNode;
    }

    /*
     * @see org.apache.maven.plugin.Mojo#execute()
     */
    public void execute() throws MojoExecutionException, MojoFailureException {
        if (isSkip()) {
            getLog().info("Skipping plugin execution");
            return;
        }

        try {
            // TODO: note that filter does not get applied due to MNG-3236
            ArtifactFilter artifactFilter = createResolvingArtifactFilter();
            rootNode = dependencyGraphBuilder.buildDependencyGraph(project, artifactFilter);

            doJarConflictCheck(rootNode);

            String dependencyTreeString = serializeDependencyTree(rootNode);
            this.getLog().info("=================Dependency Tree Below=================");
            DependencyUtil.log(dependencyTreeString, getLog());
        } catch (Exception e) {
            throw new MojoExecutionException("Cannot build project dependency graph", e);
        }
    }

    private void doJarConflictCheck(DependencyNode rootNode) {
        Map<String, String> jarVersionMap = new HashMap<String, String>();
        int conflictFoundCount = 0;
        conflictFoundCount = loopAllDependency(rootNode, jarVersionMap, conflictFoundCount);

        if (conflictFoundCount > 0) {
            this.getLog().info(String.format("Total %s Jar Version Conflict Found.", conflictFoundCount));
        } else {
            this.getLog().info("No Jar Version Conflict Found.");
        }
    }

    private int loopAllDependency(DependencyNode rootNode, Map<String, String> jarVersionMap, int conflictFoundCount) {
        List<DependencyNode> children = rootNode.getChildren();
        for (DependencyNode child : children) {
            String key = getKey(child.getArtifact());
            if (jarVersionMap.containsKey(key)) {
                if (!child.getArtifact().getVersion().equals(jarVersionMap.get(key))) {
                    if (conflictFoundCount == 0) {
                        this.getLog().info("=================Conflict Jar Version Found Below=================");
                    }
                    this.getLog().info(
                            String.format(">>>Jar %s Version Conflict : %s vs %s", key, jarVersionMap.get(key), child.getArtifact()
                                    .getVersion()));
                    conflictFoundCount++;
                }
            } else {
                jarVersionMap.put(key, child.getArtifact().getVersion());
            }

            conflictFoundCount = this.loopAllDependency(child, jarVersionMap, conflictFoundCount);
        }

        return conflictFoundCount;
    }

    private String getKey(Artifact artifact) {
        String key = String.format("%s:%s:%s:%s", artifact.getGroupId(), artifact.getArtifactId(), artifact.getType(),
                artifact.getScope());
        return key;
    }

    /**
     * Gets the artifact filter to use when resolving the dependency tree.
     * 
     * @return the artifact filter
     */
    private ArtifactFilter createResolvingArtifactFilter() {
        ArtifactFilter filter;

        // filter scope
        if (scope != null) {
            getLog().debug("+ Resolving dependency tree for scope '" + scope + "'");

            filter = new ScopeArtifactFilter(scope);
        } else {
            filter = null;
        }

        return filter;
    }

    /**
     * Serializes the specified dependency tree to a string.
     * 
     * @param rootNode
     *            the dependency tree root node to serialize
     * @return the serialized dependency tree
     */
    private String serializeDependencyTree(DependencyNode rootNode) {
        StringWriter writer = new StringWriter();
        DependencyNodeVisitor visitor = getSerializingDependencyNodeVisitor(writer);

        // TODO: remove the need for this when the serializer can calculate last
        // nodes from visitor calls only
        visitor = new BuildingDependencyNodeVisitor(visitor);
        rootNode.accept(visitor);
        return writer.toString();
    }

    public DependencyNodeVisitor getSerializingDependencyNodeVisitor(Writer writer) {
        if ("graphml".equals(outputType)) {
            return new GraphmlDependencyNodeVisitor(writer);
        } else if ("tgf".equals(outputType)) {
            return new TGFDependencyNodeVisitor(writer);
        } else if ("dot".equals(outputType)) {
            return new DOTDependencyNodeVisitor(writer);
        } else {
            return new SerializingDependencyNodeVisitor(writer, toGraphTokens(tokens));
        }
    }

    /**
     * Gets the graph tokens instance for the specified name.
     * 
     * @param tokens
     *            the graph tokens name
     * @return the <code>GraphTokens</code> instance
     */
    private GraphTokens toGraphTokens(String tokens) {
        GraphTokens graphTokens;

        if ("whitespace".equals(tokens)) {
            getLog().debug("+ Using whitespace tree tokens");

            graphTokens = SerializingDependencyNodeVisitor.WHITESPACE_TOKENS;
        } else if ("extended".equals(tokens)) {
            getLog().debug("+ Using extended tree tokens");

            graphTokens = SerializingDependencyNodeVisitor.EXTENDED_TOKENS;
        } else {
            graphTokens = SerializingDependencyNodeVisitor.STANDARD_TOKENS;
        }

        return graphTokens;
    }

}
