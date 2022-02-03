package com.ssafy.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.ssafy.api.service.BoardService;

import io.swagger.annotations.Api;

@Api(value = "board API", tags = {"Board"})
@RestController
@RequestMapping("/api/v1/board")
public class BoardController {

	@Autowired
	BoardService boardService;
}
