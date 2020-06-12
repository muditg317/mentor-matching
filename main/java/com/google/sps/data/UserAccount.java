package com.google.sps.data;

import com.google.appengine.api.datastore.DatastoreService;
import com.google.appengine.api.datastore.DatastoreServiceFactory;
import com.google.appengine.api.datastore.Entity;
import com.google.appengine.api.datastore.PreparedQuery;
import com.google.appengine.api.datastore.Query;
import com.google.appengine.api.users.User;

public class UserAccount {

  public static final String ENTITY_TYPE = "UserAccount";

  private String nickname;
  private String userID;

  public UserAccount(String nickname, String userID) {
    this.nickname = nickname;
    this.userID = userID;
  }

  public UserAccount(Entity entity) {
    this.nickname = (String) entity.getProperty("nickname");
    this.userID = (String) entity.getProperty("userID");
  }

  public static boolean accountExists(User user) {
    String userID = user.getUserId();
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query =
        new Query(UserAccount.ENTITY_TYPE)
            .setFilter(new Query.FilterPredicate("userID", Query.FilterOperator.EQUAL, userID));
    PreparedQuery results = datastore.prepare(query);
    Entity entity = results.asSingleEntity();
    return entity != null;
  }

  public static boolean nicknameAvailable(String nickname) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query =
        new Query(UserAccount.ENTITY_TYPE)
            .setFilter(new Query.FilterPredicate("nickname", Query.FilterOperator.EQUAL, nickname));
    PreparedQuery results = datastore.prepare(query);
    Entity entity = results.asSingleEntity();
    return entity == null;
  }

  public static String getUserNickname(String userID) {
    DatastoreService datastore = DatastoreServiceFactory.getDatastoreService();
    Query query =
        new Query(UserAccount.ENTITY_TYPE)
            .setFilter(new Query.FilterPredicate("userID", Query.FilterOperator.EQUAL, userID));
    PreparedQuery results = datastore.prepare(query);
    Entity entity = results.asSingleEntity();
    return (String) entity.getProperty("nickname");
  }
}
