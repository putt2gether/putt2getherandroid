package com.putt2gether.network;

/**
 * Created by Ajay on 17/06/2016.
 */
public interface PUTTAPI {

    String BASE_URL = "http://putt2gether.com/puttdemo/";
    String USER_LOGIN_URL = "http://putt2gether.com/puttdemo/login";
    String SIGN_UP_URL = "http://putt2gether.com/puttdemo/register";
    String FORGOT_PASSWORD_URL = "http://putt2gether.com/puttdemo/forgetpassword";
    String RESET_PASSWORD_URL = "http://putt2gether.com/puttdemo/updatepassword";
    String OTP_VERIFY_URL = "http://putt2gether.com/puttdemo/verifyotp";
    String GOLF_COURSE_NEAREST_LIST_URL = "http://putt2gether.com/puttdemo/getnearestgolfcourse";
    String SEARCH_GOLF_COURSE_URL = "http://putt2gether.com/puttdemo/getgolfcourselist";
    String RECENT_GOLF_COURSE_URL = "http://putt2gether.com/puttdemo/getrecentgolfcourselist";
    String FORMATE_LIST_URL = "http://putt2gether.com/puttdemo/getstrokeplaylist";
    String COUNTRY_LIST_URL = "http://putt2gether.com/puttdemo/getcountrylist";
    String STATE_LIST_URL = "http://putt2gether.com/puttdemo/getstatelist";
    String CITYLISTURL="http://putt2gether.com/puttdemo/getcitylist";
    String TEAM_INDIVDUAL_URL = "http://putt2gether.com/puttdemo/geteventformatlist";
    String TEE_LIST_URL = "http://putt2gether.com/puttdemo/getgolfcoursetee";
    String SUGGESTION_LIST_URL = "http://putt2gether.com/puttdemo/getsuggessionfriendlist";
    String GROUP_LIST_URL = "http://putt2gether.com/puttdemo/getgrouplist";
    String HOLE_NUMBER_SHOW = "http://putt2gether.com/puttdemo/showholenumbers";
    String SOCIAL_LOGIN_URL = "http://putt2gether.com/puttdemo/sociallogin";
    String UPDATE_USER_PROFILE = "http://putt2gether.com/puttdemo/updateprofile";
    String USER_PROFILE_URL = "http://putt2gether.com/puttdemo/getuserdetail";

    String CREATE_EVENT_POST = "http://putt2gether.com/puttdemo/createevent";
    String EVENT_INVITATION_LIST = "http://putt2gether.com/puttdemo/geteventinvitationlist";
    String EVENT_INVITATION_DETAIL = "http://putt2gether.com/puttdemo/eventdetail";
    String ACCEPT_REJECT_INVITE_API = "http://putt2gether.com/puttdemo/accepteventinvitation";
    String ACCEPT_REJECT_EVENT_URL = "http://putt2gether.com/puttdemo/accepteventrequest";
    String EDIT_EVENT_URL = "http://putt2gether.com/puttdemo/editevent";
    String ADD_GOLF_COURSE = "http://putt2gether.com/puttdemo/createtemporarygolfcourse";
    String GET_EVENT_LISTING="http://putt2gether.com/puttdemo/geteventperdate";
    String GET_EVENT_DATES="http://putt2gether.com/puttdemo/geteventperyearmonth";
    String GETSCORECARDDATA="http://putt2gether.com/puttdemo/getscorecarddata";
    String STARTEVENTAPI="http://putt2gether.com/puttdemo/startevent";
    String NOTIFICATIONAPI="http://putt2gether.com/puttdemo/getusernotification";
    String GETSCORERLIST="http://putt2gether.com/puttdemo/getscorerlist";
    String SAVE_PLAYER_SCORE="http://putt2gether.com/puttdemo/savescorecard";
    String GETLATESTFULLSCORE="http://putt2gether.com/puttdemo/getlatestfullscore";
    String PARTICIPANT_LIST_URL="http://putt2gether.com/puttdemo/geteventparticipentlist";
    String EditHandiCAP_URL = "http://putt2gether.com/puttdemo/updateuserhandicap";
    String NEW_SCORE_CARD_URL = "http://putt2gether.com/puttdemo/getscoreboard";
    String GET_LEADERBOARD_URL="http://putt2gether.com/puttdemo/getleaderboarddata";
    String GET_LEADERBOARD_INDIVIDUAL_URL="http://putt2gether.com/puttdemo/getleaderboard";
    String GET_PAR_INDEX_VALUE  = "http://putt2gether.com/puttdemo/getparindexvalue";
    String VIEW_REQUEST_LIST_URL = "http://putt2gether.com/puttdemo/geteventrequestlist";
    String END_ROUND_URL = "http://putt2gether.com/puttdemo/endscore";
    String SAVE_SUBMIT_ROUND_URL = "http://putt2gether.com/puttdemo/submitscore";
    String REQUEST_TO_PARTICIPATE_URL = " http://putt2gether.com/puttdemo/requesttoparticipate";
    String EVENT_HISTORY_URL = "http://putt2gether.com/puttdemo/myeventhistory";

    String GET_GROUP_LIST_URL = "http://putt2gether.com/puttdemo/getgrouplist";
    String GROUP_MEMBERS_BY_GROUPS = "http://putt2gether.com/puttdemo/getgroupmemberbygroup";
    String CREATE_GROUP_URL = "http://putt2gether.com/puttdemo/addnewgroup";
    String ADD_MEMBERS_InGroup = "http://putt2gether.com/puttdemo/addgroupmember";
    String GROUP_DETAIL_URL = "http://putt2gether.com/puttdemo/getgroupdetail";
    String GROUP_EDIT_URL = "http://putt2gether.com/puttdemo/editgroup";

    String UPCOMING_EVENT_URL = "http://putt2gether.com/puttdemo/getdashboardupcomingevent";

    String DELEGATE_LIST_URL = "http://putt2gether.com/puttdemo/getdelegateuserlist";
    String POST_DELEGATE_URL = "http://putt2gether.com/puttdemo/makedelegate";

    String PIECHART_STATS_URL = "http://putt2gether.com/puttdemo/getstats";
    String TEMP_SAVE_SCORE = "http://putt2gether.com/puttdemo/savescorecardtemp";
    String OTHER_USER_PROFILE = "http://putt2gether.com/puttdemo/viewuserprofile";
    String HOME_GOLF_URL = "http://putt2gether.com/puttdemo/getcountrygolfcourselist";
    String ADD_TO_GROUP  = "http://putt2gether.com/puttdemo/addmembertomultiplegroup";
    String ADD_GROUP_MEMBERS = "http://putt2gether.com/puttdemo/getgroupsuggessionfriendlist";
    String EXPAND_SCORE_VIEW = "http://putt2gether.com/puttdemo/getexpandablescoreview";

    String ADD_PARTICIPANT_URL = "http://putt2gether.com/puttdemo/addparticipantinevent";

    String LOGOUT_API = "http://putt2gether.com/puttdemo/logout";
    String READ_NOTIFICATION_URL = "http://putt2gether.com/puttdemo/marknotificationisread";
    String PRIVACY_POLICY_API = "http://putt2gether.com/puttdemo/privacypolicy";
    String GET_BANNER_API = "http://putt2gether.com/puttdemo/getadvbanner";
    String  SCORECARD_TEMPLATE = "http://putt2gether.com/puttdemo/sendscorecardmail";

}
