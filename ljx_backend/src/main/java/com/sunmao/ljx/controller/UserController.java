package com.sunmao.ljx.controller;

import com.sunmao.ljx.common.Result;
import com.sunmao.ljx.entity.User;
import com.sunmao.ljx.service.FollowService;
import com.sunmao.ljx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

/**
 * 用户控制器
 */
@RestController
@RequestMapping("/api/user")
public class UserController {

    @Autowired
    private UserService userService;

    @Autowired
    private FollowService followService;

    @PostMapping("/register")
    public Result<User> register(@RequestParam String account,
                                  @RequestParam String password,
                                  @RequestParam String nickname) {
        String phone = account.contains("@") ? null : account;
        String email = account.contains("@") ? account : null;
        User user = userService.register(phone, email, password, nickname);
        return Result.success(user);
    }

    @PostMapping("/login")
    public Result<Map<String, Object>> login(@RequestParam String account,
                                              @RequestParam String password) {
        User user = userService.login(account, password);
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getUserId());
        result.put("nickname", user.getNickname());
        result.put("avatar", user.getAvatar());
        result.put("studyHours", user.getStudyHours());
        return Result.success(result);
    }

    @PostMapping("/wxLogin")
    public Result<Map<String, Object>> wxLogin(@RequestParam String openid,
                                                @RequestParam(required = false) String unionid,
                                                @RequestParam(required = false) String nickname,
                                                @RequestParam(required = false) String avatar) {
        User user = userService.wxLogin(openid, unionid, nickname, avatar);
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getUserId());
        result.put("nickname", user.getNickname());
        result.put("avatar", user.getAvatar());
        result.put("studyHours", user.getStudyHours());
        return Result.success(result);
    }

    @GetMapping("/info/{userId}")
    public Result<Map<String, Object>> getUserInfo(@PathVariable Integer userId) {
        User user = userService.getById(userId);
        if (user == null) {
            return Result.error("用户不存在");
        }
        Map<String, Object> result = new HashMap<>();
        result.put("userId", user.getUserId());
        result.put("nickname", user.getNickname());
        result.put("avatar", user.getAvatar());
        result.put("studyHours", user.getStudyHours());
        result.put("followCount", followService.getFollowCount(userId));
        result.put("followerCount", followService.getFollowerCount(userId));
        return Result.success(result);
    }

    @PostMapping("/update")
    public Result<Void> updateUser(@RequestBody User user) {
        userService.updateById(user);
        return Result.success();
    }
}
