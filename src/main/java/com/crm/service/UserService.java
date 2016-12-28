package com.crm.service;

import com.crm.model.datatable.UserShallow;
import com.crm.model.entities.User;
import com.crm.repository.UserRepository;
import com.crm.security.Role;
import com.crm.util.DataTableRequest;
import com.crm.util.DataTableResponse;
import com.crm.util.SessionUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

/**
 * Service class for executing operations of the {@link User} model object..
 */
@Service("userService")
public class UserService {

    @Autowired
    private UserRepository userRepository;

    @Value("${users.predefined.password}")
    private String definedInitialPassword;

    public User findByEmailAddress(final String mail) {
        return userRepository.findByMail(mail);
    }

    public void changePassword(String password) {
        User currentUser = SessionUtils.GetCurrentUser();
        currentUser.setPassword(password);
        currentUser.setFirstLogin(false);

        userRepository.save(currentUser);
    }

    public List<User> getAllClients() {
        return userRepository.findAll();
    }

    public boolean createUser(User client) {
        User user = this.findByEmailAddress(client.getMail());
        if (client.getId() == null && user != null) {
            return false;
        }
        client.setRole(Role.ADMIN);
        client.setPassword(definedInitialPassword);
        client.setFirstLogin(true);

        userRepository.save(client);
        return true;
    }

    public User getClientById(int id) {
         return userRepository.findOne(id);
    }

    public DataTableResponse getDataTableResponse(DataTableRequest dataTableRequest) {
        PageRequest pageRequest = ServiceHelper.getPageRequest(dataTableRequest);
        String searchWord = dataTableRequest.getSearch().get("value");
        Page<User> clientPage = userRepository.findUsers(searchWord, pageRequest);;
        return getDataTable(clientPage, dataTableRequest.getDraw());
    }

    private DataTableResponse getDataTable(Page<User> clientPage, int draw) {
        DataTableResponse response = new DataTableResponse<User>();
        List<UserShallow> dataSource = getShallowUser(clientPage.getContent());
        response.setDraw(draw);
        response.setRecordsTotal((int) clientPage.getTotalElements());
        response.setRecordsFiltered((int) clientPage.getTotalElements());
        response.setData(dataSource.toArray());

        return response;
    }

    private List<UserShallow> getShallowUser(List<User> users) {
        List<UserShallow> shallowUser = new ArrayList<>();
        for (User user : users) {
            UserShallow clientShallow = new UserShallow();
            clientShallow.setId(user.getId());
            clientShallow.setMail(user.getMail());
            clientShallow.setName(user.getName());

            shallowUser.add(clientShallow);
        }
        return shallowUser;
    }
}
