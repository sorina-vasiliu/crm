package com.crm.service;

import com.crm.model.datatable.HomeShallow;
import com.crm.model.entities.Home;
import com.crm.model.entities.User;
import com.crm.model.entities.UserHome;
import com.crm.repository.HomeRepository;
import com.crm.repository.UserHomeRepository;
import org.apache.commons.lang3.StringEscapeUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dragos on 4/23/2016.
 */
@Service("HomeService")
public class HomeService {
    @Autowired
    private HomeRepository homeRepository;
    @Autowired
    private UserHomeRepository userHomeRepository;

    public HomeShallow addWywywig(String htmlForWysiwyg, List<Integer> userIds) {
        Home home = new Home();
        home.setHtml(htmlForWysiwyg);
        Home responseHome = homeRepository.saveAndFlush(home);

        List<UserHome> homeUsers = new ArrayList<>();
        for (int id : userIds) {
            User user = new User();
            user.setId(id);

            UserHome userHome = new UserHome();
            userHome.setUser(user);
            userHome.setHome(responseHome);

            homeUsers.add(userHome);
        }
        userHomeRepository.save(homeUsers);

        return getShallowHome(responseHome);
    }

    public void updateWywywig(String htmlWysiwyg, int id) {
        Home home = new Home();
        home.setHtml(htmlWysiwyg);
        home.setId(id);
        homeRepository.save(home);
    }

    public List<HomeShallow> getWywywig(List<Integer> usersId) {
        List<UserHome> usersHome = userHomeRepository.getUserHomeByUsersId(usersId);
        List<HomeShallow> home = new ArrayList<>();
        for (UserHome userHome : usersHome) {
            HomeShallow homeShallow = getShallowHome(userHome.getHome());
            if (!home.contains(homeShallow)) {
                home.add(homeShallow);
            }
        }
        return home;
    }

    public void delete(int id) {
        userHomeRepository.delete(id);
        homeRepository.delete(id);
    }

    public List<String> getUserHome(Integer usersId) {
        List<UserHome> usersHome = userHomeRepository.getUserHomeByUserId(usersId);
        List<String> homeInfo = new ArrayList<>();
        for (UserHome info : usersHome) {
            homeInfo.add(StringEscapeUtils.unescapeHtml4(info.getHome().getHtml()));
        }
        return homeInfo;
    }

    private HomeShallow getShallowHome(Home home) {
        HomeShallow homeShallow = new HomeShallow();
        homeShallow.setId(home.getId());
        homeShallow.setHtml(home.getHtml());

        return homeShallow;
    }
}
