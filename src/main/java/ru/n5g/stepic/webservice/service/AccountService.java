package ru.n5g.stepic.webservice.service;

import ru.n5g.stepic.webservice.model.UserProfile;

import java.util.HashSet;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author Gleb Belyaev
 * @version 1.0
 * @since 24.12.15
 */
public class AccountService {
    private Map<String, UserProfile> users = new ConcurrentHashMap<>();

    private HashSet<UserProfile> userHashSet = new HashSet<>();

    public void saveUser(UserProfile user) {
        userHashSet.add(user);
    }

    public boolean isCorrectUser(UserProfile user) {
        return userHashSet.contains(user);
    }
}
