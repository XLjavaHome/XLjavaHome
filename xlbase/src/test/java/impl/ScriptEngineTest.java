package impl;

import java.util.List;
import javax.script.*;
import org.junit.Test;

/**
 * Created with 徐立.可以直接调用js
 *
 * @author 徐立
 * @date 2019-06-16
 * @time 17:20
 * To change this template use File | Settings | File Templates.
 */
public class ScriptEngineTest {
    @Test
    public void name() {
        ScriptEngineManager scriptEngineManager = new ScriptEngineManager();
        ScriptEngine ENGINE = scriptEngineManager.getEngineByName("JavaScript");
    }
    
    @Test
    public void name2() {
        ScriptEngineManager manager = new ScriptEngineManager();
        List<ScriptEngineFactory> factories = manager.getEngineFactories();
        for (ScriptEngineFactory f : factories) {
            System.out.println(f.getEngineName());
            System.out.println(f.getNames());
        }
    }
    
    @Test
    public void demo() throws ScriptException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        String script = "print ('hello script')";
        engine.eval(script);
    }
    
    @Test
    public void 传递变量() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine;
        engine = manager.getEngineByName("js");
        engine = manager.getEngineByName("javascript");
        engine.put("a", 4);
        engine.put("b", 6);
        Object maxNum;
        maxNum = engine.eval("function max_num(a,b){return (a>b)?a:b;}max_num(a,b);");
        Invocable invocable = (Invocable) engine;
        System.out.println("max_num:" + maxNum);
        System.out.println(invocable.invokeFunction("max_num", 4, 6));
    }
    
    @Test
    public void 动态调用() throws ScriptException, NoSuchMethodException {
        ScriptEngineManager manager = new ScriptEngineManager();
        ScriptEngine engine = manager.getEngineByName("js");
        //用Compilable提高性能
        Compilable compEngine = (Compilable) engine;
        CompiledScript script = compEngine.compile("function max_num(a,b){return (a>b)?a:b;}");
        script.eval();
        Invocable invoke = (Invocable) engine;
        Object maxNum = invoke.invokeFunction("max_num", 4, 6);
        System.out.println(maxNum);
    }
}
