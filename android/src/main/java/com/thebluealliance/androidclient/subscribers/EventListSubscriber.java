package com.thebluealliance.androidclient.subscribers;

import com.thebluealliance.androidclient.adapters.ListViewAdapter;
import com.thebluealliance.androidclient.helpers.EventHelper;
import com.thebluealliance.androidclient.listitems.ListItem;
import com.thebluealliance.androidclient.models.Event;

import java.util.ArrayList;
import java.util.List;

/**
 * Bind a list of events to a {@link ListViewAdapter}
 */
public class EventListSubscriber extends BaseAPISubscriber<List<Event>, List<ListItem>> {

    public static final int
      MODE_WEEK = 0,
      MODE_TEAM = 1,
      MODE_DISTRICT = 2;

    private int mRenderMode;

    public EventListSubscriber() {
        super();
        mDataToBind = new ArrayList<>();
        mRenderMode = MODE_WEEK;
    }

    public void setRenderMode(int renderMode) {
        mRenderMode = renderMode;
    }

    @Override
    public void parseData() {
        mDataToBind.clear();
        if (mAPIData == null) {
            return;
        }
        switch (mRenderMode) {
            case MODE_WEEK:
            default:
                EventHelper.renderEventListForWeek(mAPIData, mDataToBind);
                break;
            case MODE_TEAM:
                EventHelper.renderEventListForTeam(mAPIData, mDataToBind);
                break;
            case MODE_DISTRICT:
                EventHelper.renderEventListForDistrict(mAPIData, mDataToBind);
                break;
        }
    }
}