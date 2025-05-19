package com.pixel_game.pixel.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class GameController {

    @GetMapping("/getPlayerInfo")
    public String getPlayerInfo(@RequestParam String playerId) {
        return "Player info for playerId: " + playerId;
    }

    @GetMapping("/getStageInfo")
    public String getStageInfo(@RequestParam String stageId) {
        return "Stage info for stageId; " + stageId;
    }
}
