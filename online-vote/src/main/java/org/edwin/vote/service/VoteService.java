package org.edwin.vote.service;

import java.util.List;

import org.edwin.vote.mvc.to.OptionTO;
import org.edwin.vote.mvc.to.VoteResult;
import org.edwin.vote.mvc.to.VoteTO;

public interface VoteService {

	public List<OptionTO> getVoteOptions(int selectId) throws Exception;

	public List<VoteTO> getPersonalVoteResult(String userId, int selectId) throws Exception;

	public void vote(VoteTO voteTO) throws Exception;

	public VoteResult getVoteResult(int selectId) throws Exception;

	public boolean isVoted(String userId, int selectId) throws Exception;

}
