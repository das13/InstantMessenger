package ua.sumdu.java.lab2.instant_messenger.entities;

import ua.sumdu.java.lab2.instant_messenger.api.GroupMap;
import ua.sumdu.java.lab2.instant_messenger.common_entities.User;

import java.util.Map;
import java.util.Set;
import java.util.TreeMap;

public class GroupMapImpl implements GroupMap {

    private Map<String, UserMapImpl> map;

    public GroupMapImpl() {
        map = new TreeMap<>();
    }

    @Override
    public void addUser(String chatName, User user) {
        if (!map.containsKey(chatName)) {
            UserMapImpl users = new UserMapImpl();
            map.put(chatName, users);
        }
        map.get(chatName).addUser(user);
    }

    @Override
    public void deleteUser(String chatName, User user) {
        if (map.containsKey(chatName)) {
            map.get(chatName).removeUser(user);
        }
    }

    public Map<String, UserMapImpl> getMap() {
        return map;
    }

    public GroupMapImpl setMap(Map<String, UserMapImpl> map) {
        this.map = map;
        return this;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            System.out.println(this.getClass().toGenericString());
            return false;
        } else {
            Map current;
            if (GroupMap.class.equals(obj.getClass()) || GroupMapImpl.class.equals(obj.getClass())) {
                GroupMapImpl groupMap = (GroupMapImpl) obj;
                current = groupMap.getMap();
            } else {
                try {
                    current = (Map<String, UserMapImpl>) obj;

                } catch (ClassCastException e) {
                    return false;
                }
            }
            if (map.size() != current.size()) {
                return false;
            }
            Set<Map.Entry<String, UserMapImpl>> set = current.entrySet();
            for (Map.Entry value:set) {
                UserMapImpl users1 = (UserMapImpl) value.getValue();
                UserMapImpl users2 = map.get(value.getKey());
                if (!users1.equals(users2)) {
                    return false;
                }
            }
            return true;
        }
    }

    @Override
    public int hashCode() {
        int res = 0;
        for (Map.Entry<String, UserMapImpl> entry:map.entrySet()) {
            res = 13*res + entry.getKey().hashCode() + entry.getValue().hashCode();
        }
        return res;
    }
}
