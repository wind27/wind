package com.noriental.utils;

import java.awt.image.BufferedImage;
import java.io.Serializable;

import com.octo.captcha.image.ImageCaptcha;
 
/**
 * <p>A Gimpy is a ImagCaptcha. It is also the most common captcha.</p> <ul> <li>Challenge type : image</li>
 * <li>Response type : String</li> <li>Description : An image of a distorded word is shown. User have to recognize the
 * word and to submit it.</li> </ul>
 *
 * @author <a href="mailto:mag@jcaptcha.net">Marc-Antoine Garrigue</a>
 * @version 1.0
 */
public class GimpyCopy extends ImageCaptcha implements Serializable {
 
    private String response;
 
    public GimpyCopy(String question, BufferedImage challenge, String response) {
        super(question, challenge);
        this.response = response;
    }
 
    /**
     * Validation routine from the CAPTCHA interface. this methods verify if the response is not null and a String and
     * then compares the given response to the internal string.
     *
     * @return true if the given response equals the internal response, false otherwise.
     */
    public final Boolean validateResponse(final Object response) {
        return (null != response && response instanceof String)
                ? validateResponse((String) response) : Boolean.FALSE;
    }
 
    /**
     * Very simple validation routine that compares the given response to the internal string.
     *
     * @return true if the given response equals the internal response, false otherwise.
     */
    private final Boolean validateResponse(final String response) {
        // 主要改的这里
        return new Boolean(response.toLowerCase().equals(this.response.toLowerCase()));
    }
}