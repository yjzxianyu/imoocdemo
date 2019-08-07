package com.sy.imoocdemo.controller;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.sy.imoocdemo.dao.SeckillDao;
import com.sy.imoocdemo.dto.Exposer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpRequest;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import com.sy.imoocdemo.entity.*;
import com.sy.imoocdemo.service.*;

import java.util.Date;

@Controller
@RequestMapping("/secKill")
public class SeckillController {

    @Autowired
    private SeckillService seckillService;
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public SecKill getById( long id,
    HttpServletRequest request, HttpServletResponse response){
        SecKill secKill = seckillService.getById(id);
        return secKill;
    }


}
