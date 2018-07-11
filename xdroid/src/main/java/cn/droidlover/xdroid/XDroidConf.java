package cn.droidlover.xdroid;

import cn.droidlover.xdroid.imageloader.ILFactory;
import cn.droidlover.xdroid.imageloader.ILoader;

public class XDroidConf {
    private static XDroidConf XDroidConf;

    // #imageloader
    private int ilLoadingRes = ILoader.Options.RES_NONE;
    private int ilErrorRes = ILoader.Options.RES_NONE;
    private ILoader loader;

    private XDroidConf() {

    }

    public static XDroidConf getInstance() {
        if (XDroidConf == null)
            XDroidConf = new XDroidConf();
        return XDroidConf;
    }


    public XDroidConf setILLoadingRes(int loadingRes) {
        this.ilLoadingRes = loadingRes;
        return this;
    }

    public XDroidConf setILErrorRes(int errorRes) {
        this.ilErrorRes = errorRes;
        return this;
    }

    public XDroidConf setILoader(ILoader iLoader) {
        loader = iLoader;
        return this;
    }

    public void build() {
        ILoader.Options.initDefaultOptions(ilLoadingRes, ilErrorRes);
        ILFactory.setILoader(loader);
    }
}
