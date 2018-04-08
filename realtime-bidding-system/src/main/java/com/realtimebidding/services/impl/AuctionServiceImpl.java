package com.realtimebidding.services.impl;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoOperations;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Service;

import com.realtimebidding.dtos.AuctionBidRequestDto;
import com.realtimebidding.dtos.Response;
import com.realtimebidding.model.Auction;
import com.realtimebidding.model.BidInformation;
import com.realtimebidding.model.Product;
import com.realtimebidding.model.User;
import com.realtimebidding.repository.AuctionRepository;
import com.realtimebidding.repository.UserRepository;
import com.realtimebidding.services.AuctionService;

@Service
public class AuctionServiceImpl implements AuctionService {

	@Autowired
	AuctionRepository AuctionRepository;
	
	@Autowired
	UserRepository userRepositry;

	@Autowired
	MongoOperations mongoOperations;

	@Override
	public Auction getById(String id) throws Exception {
		return AuctionRepository.findOne(id);
	}

	@Override
	public Auction addNewAuction(Auction auction) throws Exception {
		return AuctionRepository.insert(auction);
	}

	@Override
	public Auction updateAuction(String id, Auction auction) throws Exception {
		if (AuctionRepository.exists(id))
			return AuctionRepository.save(auction);
		throw new RuntimeException("No such Auction exists with Auction id -> " + id);
	}

	@Override
	public Auction deleteAuction(String id) throws Exception {
		if (AuctionRepository.exists(id)) {
			Auction u = AuctionRepository.findOne(id);
			AuctionRepository.delete(id);
			return u;
		}
		throw new RuntimeException("No such Auction exists with Auction id -> " + id);
	}

	@Override
	public Auction getByProduct(Product product) throws Exception {
		Auction u = AuctionRepository.findByProduct(product);
		if (u != null)
			return u;
		throw new RuntimeException("No such auction exists for product ->" + product);
	}

	@Override
	public List<Auction> getAllActive() throws Exception {
		Query query = new Query();
		// query.addCriteria(Criteria.where("endTime").gt(System.currentTimeMillis()));
		List<Auction> list = mongoOperations.find(query, Auction.class);
		return list;
	}

	@Override
	public boolean addBidder(String auctionId, User user) throws Exception {
		Auction auction = this.AuctionRepository.findOne(auctionId);
		Set<User> set = auction.getParticipants();
		if (set == null || !set.contains(user)) {
			if (set == null) {
				set = new HashSet<>();
				auction.setParticipants(set);
			}
			set.add(user);

			this.AuctionRepository.save(auction);
			return true;
		}
		return false;
	}

	@Override
	public Auction addBid(String auctionId, String userId, double amount) throws Exception {
		User user = userRepositry.findById(userId);
		addBidder(auctionId,user);
		
		Auction auction = this.AuctionRepository.findOne(auctionId);
		List<BidInformation> list = auction.getBiddings();

		if (list == null) {
			list = new ArrayList<>();
			auction.setBiddings(list);
		}
		BidInformation info = new BidInformation();
		info.setAmount(amount);
		
		info.setUser(user);
		list.add(info);
		
		this.AuctionRepository.save(auction);
		
		return auction;
	}
}