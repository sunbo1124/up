package com.ericsson.csp.tsc.admin.util;

import groovy.servlet.GroovyServlet;
import groovy.util.GroovyScriptEngine;

public class ClassLoaderGroovyServlet extends GroovyServlet {

    @Override
    protected GroovyScriptEngine createGroovyScriptEngine() {
        return new GroovyScriptEngine(this,this.getClass().getClassLoader());
    }
    
    

}
