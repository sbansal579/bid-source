package com.realtimebidding.services;

import java.util.List;

import com.realtimebidding.dtos.AuctionBidRequestDto;
import com.realtimebidding.dtos.Response;
import com.realtimebidding.model.Auction;
import com.realtimebidding.model.Product;
import com.realtimebidding.model.User;

public interface AuctionService {

	Auction getById(String id) throws Exception;

	List<Auction> getAllActive() throws Exception;

	Auction addNewAuction(Auction auction) throws Exception;

	Auction updateAuction(String id, Auction auction) throws Exception;

	Auction deleteAuction(String id) throws Exception;

	Auction getByProduct(Product product) throws Exception;

	boolean addBidder(String auctionId, User user) throws Exception;

	Auction addBid(String auctionId, String userId, double amount) throws Exception;

}
