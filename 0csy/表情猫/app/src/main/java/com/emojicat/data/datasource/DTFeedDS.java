package com.emojicat.data.datasource;

import com.avos.avoscloud.AVObject;
import com.avos.avoscloud.AVQuery;
import com.avos.avoscloud.AVUser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * Created by fanglin on 2/27/16.
 */
public class DTFeedDS extends AVQueryDataSourceBase {

    public DTFeedDS() {
        super();
        this.reset();
    }

    @Override
    public void reset() {
        super.reset();
        //设置了不看某人的动态
        AVQuery<AVObject> doNotWhantToSomene = AVQuery.getQuery("UserPrefs");
        doNotWhantToSomene.whereEqualTo("owner", AVUser.getCurrentUser());
        doNotWhantToSomene.whereEqualTo("blockUserFlow", true);

        AVQuery<AVObject> queryArticle1 = AVQuery.getQuery("Article");
        queryArticle1.whereDoesNotMatchKeyInQuery("owner", "user", doNotWhantToSomene);


        //某人设置了不让我看他的动态
        AVQuery<AVObject> noAccessToSomene = AVQuery.getQuery("UserPrefs");
        noAccessToSomene.whereEqualTo("user", AVUser.getCurrentUser());
        noAccessToSomene.whereEqualTo("blockUserViewMyFlow", true);

        AVQuery<AVObject> queryArticle2 = AVQuery.getQuery("Article");
        queryArticle2.whereDoesNotMatchKeyInQuery("owner", "owner", noAccessToSomene);
        AVQuery<AVObject> queryArticle = null;


        boolean flowOnlyFriends = false;
        boolean flowCompany = false;
        boolean flowRegion = false;
        if (AVUser.getCurrentUser()!=null){

            if (AVUser.getCurrentUser().has("flowOnlyFriends")){
                flowOnlyFriends = AVUser.getCurrentUser().getBoolean("flowOnlyFriends");
            }

            if (AVUser.getCurrentUser().has("flowCompany")){
                if (AVUser.getCurrentUser().get("flowCompany")!=null){
                    flowCompany = true;
                }
            }

            if (AVUser.getCurrentUser()!=null){
                if (AVUser.getCurrentUser().has("flowRegion")){
                    if (AVUser.getCurrentUser().get("flowRegion")!=null){
                        flowRegion = true;
                    }
                }
            }
        }

        if (  flowOnlyFriends  || flowRegion  || flowCompany ) {
            //AVQuery<AVObject> queryArticle3 = new AVQuery<AVObject>("Article");
            AVQuery<AVObject> queryArticle3 = AVQuery.getQuery("Article");
            if (flowOnlyFriends ) {
                //AVQuery<AVObject> friendQuery = new AVQuery<AVObject>("Friend");
                AVQuery<AVObject> friendQuery = AVQuery.getQuery("Friend");
                friendQuery.whereEqualTo("owner", AVUser.getCurrentUser());


                //AVQuery<AVObject> query1 = new AVQuery<AVObject>("Article");
                AVQuery<AVObject> query1 = AVQuery.getQuery("Article");
                query1.whereMatchesKeyInQuery("owner", "theFriend", friendQuery);

                //AVQuery<AVObject> query2 = new AVQuery<AVObject>("Article");
                AVQuery<AVObject> query2 = AVQuery.getQuery("Article");
                query2.whereEqualTo("owner", AVUser.getCurrentUser());


                List<AVQuery<AVObject>> orQueries = new ArrayList<AVQuery<AVObject>>();
                orQueries.add(query1);
                orQueries.add(query2);

                queryArticle3 = AVQuery.or(orQueries);

            } else {//公司和地区筛选

                // AVQuery<AVObject> userQuery = new AVQuery<AVObject>("User");
                AVQuery<AVObject> userQuery = AVQuery.getQuery("_User");
                if (flowCompany) {
                    userQuery.whereEqualTo("company", AVUser.getCurrentUser().get("flowCompany"));
                }
                if (flowRegion) {

                    Map hashMap = AVUser.getCurrentUser().getMap("flowRegion");

                    String province = hashMap.get("province").toString();

                    userQuery.whereEqualTo("province", province);

                    if (hashMap.size() == 2) {
                        String city = hashMap.get("city").toString();//不限的话为null
                        userQuery.whereEqualTo("city", city);
                    }
                }
                queryArticle3.whereMatchesQuery("owner", userQuery);

            }


            List<AVQuery<AVObject>> queryArticleSelect = new ArrayList<AVQuery<AVObject>>();
            queryArticleSelect.add(queryArticle1);
            queryArticleSelect.add(queryArticle2);
            queryArticleSelect.add(queryArticle3);
            queryArticle = AVQuery.and(queryArticleSelect);

        } else {
            List<AVQuery<AVObject>> queryArticleSelect = new ArrayList<AVQuery<AVObject>>();
            queryArticleSelect.add(queryArticle1);
            queryArticleSelect.add(queryArticle2);
            queryArticle = AVQuery.and(queryArticleSelect);
        }



        queryArticle.include("owner");
        queryArticle.include("owner.company");
        queryArticle.include("images");
        queryArticle.include("company");
        queryArticle.include("zanUserSnapshot");
        queryArticle.include("relateUsers");
        queryArticle.include("tags");
        queryArticle.include("zanUserIdList");
        queryArticle.include("zanUserIdList.company");
        queryArticle.include("shareUserIdList");
        queryArticle.include("shareUserIdList.company");
        queryArticle.include("commentUserIdList");
        queryArticle.include("commentUserIdList.company");
        queryArticle.include("zanUserSnapshot.company");
        queryArticle.include("shareUserSnapshot.company");
        queryArticle.include("shareUserSnapshot");
        queryArticle.include("commentSnapshot");
        queryArticle.include("commentSnapshot.owner");
        queryArticle.include("commentSnapshot.to");
        queryArticle.addDescendingOrder("createdAt");
        this.query = queryArticle;
    }
}
