package cn.droidlover.xdroid.bus;

/**
 * Created by wanglei on 2016/12/2.
 */

public class BusFactory {
    private static IBus bus;

    public enum BusType {
        EVENT_BUS,
        RX_BUS
    }

    public static IBus getBus(BusType type) {
        switch (type) {
            case EVENT_BUS:
                if (bus == null) {
                    synchronized (BusFactory.class) {
                        if (bus == null) {
                            bus = new EventBusImpl();
                        }
                    }
                }
                break;
            case RX_BUS:
                return RxBusImpl.get(); // RxBusImpl本来就是单例模式
        }
        return bus;
    }
}
