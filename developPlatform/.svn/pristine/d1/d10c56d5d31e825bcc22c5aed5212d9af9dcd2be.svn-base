package com.tengzhi.IM.Dwr.ScriptSession;

import org.directwebremoting.impl.DefaultScriptSessionManager;

/**
 * @author 鱼游浅水
 * @create 2020-05-31
 * 在管理器中绑定监听器
 */
public class DWRScriptSessionManager extends DefaultScriptSessionManager {

    /**
     * 定义无参构造 在DWR默认管理器中绑定 我的监听实现类
     */
    public DWRScriptSessionManager(){
        //绑定一个ScriptSession增加销毁事件的监听器
        this.addScriptSessionListener( new DWRScriptSessionListener());
        System. out.println( "bind DWRScriptSessionListener");
    }

}
