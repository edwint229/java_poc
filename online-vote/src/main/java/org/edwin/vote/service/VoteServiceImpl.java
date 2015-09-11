package org.edwin.vote.service;

import java.util.ArrayList;
import java.util.List;

import org.edwin.vote.dao.OptionDAO;
import org.edwin.vote.dao.VoteDAO;
import org.edwin.vote.mvc.to.OptionTO;
import org.edwin.vote.mvc.to.VoteData;
import org.edwin.vote.mvc.to.VoteResult;
import org.edwin.vote.mvc.to.VoteTO;

public class VoteServiceImpl implements VoteService {

    private VoteDAO voteDAO;

    private OptionDAO optionDAO;

    public VoteDAO getVoteDAO() {
        return voteDAO;
    }

    public void setVoteDAO(VoteDAO voteDAO) {
        this.voteDAO = voteDAO;
    }

    public OptionDAO getOptionDAO() {
        return optionDAO;
    }

    public void setOptionDAO(OptionDAO optionDAO) {
        this.optionDAO = optionDAO;
    }

    @Override
    public VoteResult getVoteResult(int selectId) throws Exception {
        VoteResult voteResult = new VoteResult();
        List<OptionTO> options = optionDAO.getOptions(selectId);
        List<String> labels = new ArrayList<String>();
        List<Integer> data = new ArrayList<Integer>();
        for (OptionTO option : options) {
            labels.add(option.getShortName());
            int count = voteDAO.getVoteOptionCount(option.getId());
            data.add(count);
        }

        voteResult.setLabels(labels);

        List<VoteData> datasets = new ArrayList<VoteData>();
        VoteData voteData = new VoteData();
        voteData.setFillColor("rgba(0,128,0,0.5)");
        voteData.setStrokeColor("rgba(0,128,0,1)");

        voteData.setData(data);

        datasets.add(voteData);
        voteResult.setDatasets(datasets);

        return voteResult;
    }

    @Override
    public List<OptionTO> getVoteOptions(int selectId) throws Exception {
        return optionDAO.getOptions(selectId);
    }

    @Override
    public void vote(VoteTO voteTO) throws Exception {
        voteDAO.vote(voteTO);
    }

    @Override
    public boolean isVoted(String userId, int selectId) throws Exception {
        return 1 == voteDAO.getCount(userId, selectId);
    }

	@Override
	public List<VoteTO> getPersonalVoteResult(String userId, int selectId) throws Exception {
		return  voteDAO.getPersonalVoteResult(userId, selectId);
	}

}
