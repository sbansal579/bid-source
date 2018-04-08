package com.realtimebidding.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.realtimebidding.dtos.Response;
import com.realtimebidding.dtos.ResponseTypeEnum;
import com.realtimebidding.model.Auction;
import com.realtimebidding.services.AuctionService;

@RestController
@RequestMapping("auction")
public class AuctionController {

	@Autowired
	AuctionService AuctionService;

	//@RequestMapping(value = "/{AuctionId}", method = RequestMethod.GET)
	@GetMapping(value="{AuctionId}")
	public Response<?> getAuctionById(@PathVariable("AuctionId") String AuctionId) {
		try {
			return new Response<Auction>(ResponseTypeEnum.SUCCESS, AuctionService.getById(AuctionId));
		} catch (Exception e) {
			e.printStackTrace();
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	//@RequestMapping(value = "/", method = RequestMethod.POST)
	@PostMapping
	public Response<?> addNewAuction(@RequestBody Auction Auction) {
		try {
			return new Response<Auction>(ResponseTypeEnum.SUCCESS, AuctionService.addNewAuction(Auction));
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	//@RequestMapping(value = "/", method = RequestMethod.GET)
	@GetMapping
	public Response<?> getAllAuctions() {
		try {
			return new Response<List<Auction>>(ResponseTypeEnum.SUCCESS, AuctionService.getAllActive());
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	//@RequestMapping(value = "/{AuctionId}", method = RequestMethod.PUT)
	@PutMapping(value="{AuctionId}")
	public Response<?> updateAuction(@PathVariable("AuctionId") String AuctionId, @RequestBody Auction Auction) {
		try {
			return new Response<Auction>(ResponseTypeEnum.SUCCESS, AuctionService.updateAuction(AuctionId, Auction));
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

	//@RequestMapping(value = "/{AuctionId}", method = RequestMethod.DELETE)
	@DeleteMapping(value="{AuctionId}")
	public Response<?> deleteAuctionById(@PathVariable("AuctionId") String AuctionId) {
		try {
			return new Response<Auction>(ResponseTypeEnum.SUCCESS, AuctionService.deleteAuction(AuctionId));
		} catch (Exception e) {
			return new Response<String>(ResponseTypeEnum.ERROR, e.getMessage());
		}
	}

}
