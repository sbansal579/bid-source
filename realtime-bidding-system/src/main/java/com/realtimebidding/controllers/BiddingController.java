package com.realtimebidding.controllers;

import javax.annotation.Resource;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.realtimebidding.dtos.Response;
import com.realtimebidding.dtos.ResponseTypeEnum;
import com.realtimebidding.model.Auction;
import com.realtimebidding.model.User;
import com.realtimebidding.services.AuctionService;

@RestController
@RequestMapping("bidding")
public class BiddingController {

	@Resource
	private AuctionService auctionService;

	@PostMapping(value = "/bidder/{autionId}")
	public Response<String> addBidder(@PathVariable("autionId") String autionId, @RequestBody User user) {
		try {
			boolean flg = this.auctionService.addBidder(autionId, user);
			if (flg) {
				return new Response<String>(ResponseTypeEnum.SUCCESS, "User Added Successfully");
			} else {
				return new Response<String>(ResponseTypeEnum.SUCCESS, "User Already Added");
			}

		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	@GetMapping(value = "/bid/{autionId}/{userId}/{amount}")
	public Response<?> addBid(@PathVariable("autionId") String autionId, @PathVariable("userId") String userId,
			@PathVariable("amount") double amount) {
		try {
			return new Response<Auction>(ResponseTypeEnum.SUCCESS,
					this.auctionService.addBid(autionId, userId, amount));
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	/*@GetMapping(value = "details/{autionId}")
	public Response<?> getDetails(@PathVariable("autionId") String autionId) {
		try {
			return new Response<Auction>(ResponseTypeEnum.SUCCESS,
					this.auctionService.addBid(autionId, userId, amount));
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}

	}*/
}
