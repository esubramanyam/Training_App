package com.api.automation;

import com.intuit.karate.junit5.Karate;

public class GetViewAllTestRunner {

    @Karate.Test
    public Karate getViewAllTest(){
        return Karate.run("OverviewOnVariables").relativeTo(getClass());
        //return Karate.run("GetPlanFindById").relativeTo(getClass());
    }

}
