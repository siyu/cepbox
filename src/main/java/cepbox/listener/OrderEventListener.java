package cepbox.listener;

import com.espertech.esper.client.EventBean;
import com.espertech.esper.client.UpdateListener;

/**
 * Created with IntelliJ IDEA.
 * User: siyu
 * Date: 4/7/13
 * Time: 11:14 PM
 * To change this template use File | Settings | File Templates.
 */
public class OrderEventListener implements UpdateListener{


    @Override
    public void update(EventBean[] eventBeans, EventBean[] eventBeans2) {
        EventBean event = eventBeans[0];
        System.out.println("avg=" + event.get("avg(price)"));
    }
}
