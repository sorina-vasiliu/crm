package com.crm.service;

import com.crm.model.datatable.HomeShallow;
import com.crm.model.entities.Home;
import com.crm.repository.HomeRepository;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by Dragos on 4/23/2016.
 */
@Service("HomeService")
public class HomeService {
    @Autowired
    private HomeRepository homeRepository;

    public HomeShallow addWywywig(String htmlForWysiwyg) {
        Home home = new Home();
        home.setHtml(htmlForWysiwyg);
        Home responseHome = homeRepository.saveAndFlush(home);

        return getShallowHome(responseHome);
    }

    public void updateWywywig(String htmlWysiwyg, int id) {
        Home home = new Home();
        home.setHtml(htmlWysiwyg);
        home.setId(id);
        homeRepository.save(home);
    }

    public List<HomeShallow> getWywywig() {
        List<Home> usersHome = homeRepository.findAll();
        List<HomeShallow> home = new ArrayList<>();
        for (Home item : usersHome) {
            HomeShallow homeShallow = getShallowHome(item);
                home.add(homeShallow);
        }
        return home;
    }

    public void delete(int id) {
        homeRepository.delete(id);
    }

    public List<String> getUserHome() {
        List<Home> usersHome = homeRepository.findAll();
        List<String> homeInfo = usersHome.stream().map(info -> StringEscapeUtils.unescapeHtml4(info.getHtml())).collect(Collectors.toList());
        return homeInfo;
    }

    private HomeShallow getShallowHome(Home home) {
        HomeShallow homeShallow = new HomeShallow();
        homeShallow.setId(home.getId());
        homeShallow.setHtml(home.getHtml());

        return homeShallow;
    }
}
