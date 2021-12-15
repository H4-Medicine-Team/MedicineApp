package com.ds.nofication.Controllers;

import com.ds.nofication.Interfaces.Callbackable;
import com.ds.nofication.Interfaces.Listenable;

public abstract class BaseApiController implements Callbackable, Listenable {
    protected String baseURl = "http://93.176.82.48/";
}
