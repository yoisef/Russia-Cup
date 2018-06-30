package com.joe.android.russiacup;


import com.joe.android.russiacup.matches.Matches;
import com.joe.android.russiacup.models.Groups;
import com.joe.android.russiacup.modelteams.Teams;
import com.joe.android.russiacup.stage.Stagematches;
import com.joe.android.russiacup.teamflag.Players;
import com.joe.android.russiacup.teamflag.Teamflag;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.Path;

public interface cupinterface  {

    @Headers("X-Auth-Token:9be95181b928472bbdc44b8984e6a971")
    @GET("467/leagueTable")
    Call<Groups> getstandings();

    @Headers("X-Auth-Token:9be95181b928472bbdc44b8984e6a971")
    @GET("467/teams")
    Call<Teams> getteams();

    @Headers("X-Auth-Token:9be95181b928472bbdc44b8984e6a971")
    @GET("fixtures?timeFrame=n4")
    Call<Matches> grtsoonmatches();

    @Headers("X-Auth-Token:9be95181b928472bbdc44b8984e6a971")
    @GET("{endpoint}")
    Call<com.joe.android.russiacup.Players.Players> getplayer(@Path("endpoint") String Endpoint);

  //  @GET("fifa/news")
 //  Call<News> getnews();

    @Headers("X-Auth-Token:9be95181b928472bbdc44b8984e6a971")
    @GET("fixtures?timeFrame=p4")
    Call<Matches> getperviousmatches();

    @Headers("X-Auth-Token:9be95181b928472bbdc44b8984e6a971")
    @GET("{endpointy}")
    Call<Stagematches> getstagematches(@Path("endpointy") String endpointY);


}
