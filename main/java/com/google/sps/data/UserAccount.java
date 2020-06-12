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
}
