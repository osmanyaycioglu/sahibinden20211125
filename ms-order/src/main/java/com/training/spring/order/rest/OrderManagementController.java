package com.training.spring.order.rest;

import javax.servlet.http.HttpServletRequest;

import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.training.spring.order.rest.models.OrderRestObj;

@RestController
@RequestMapping("/api/v1/order/management")
public class OrderManagementController {

    @PostMapping("/place")
    public String place(@Validated @RequestBody final OrderRestObj order) {
        if (order.getHour() == 13) {
            // do
        }
        return "OK";
    }

    @GetMapping("/remove/{oid}")
    public String remove(@PathVariable("oid") final Long orderId) {
        return "OK";
    }

    @GetMapping("/remove2")
    public String remove2(@RequestParam("oid") final Long orderId) {
        return "OK";
    }

    // Yapma
    @GetMapping("/dynamic/{command}")
    public ResponseEntity<?> doEverything(@PathVariable("oid") final String command,
                                          final HttpServletRequest hsr) {
        switch (command) {
            case "place": {
                String parameterLoc = hsr.getParameter("oid");
                return ResponseEntity.ok("OK");
            }
            case "remove": {
                String parameterLoc = hsr.getParameter("oid");
                return ResponseEntity.ok(13);
            }
            default:
                break;
        }
        return null;
    }


}
