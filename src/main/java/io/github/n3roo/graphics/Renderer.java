package io.github.n3roo.graphics;

import com.jogamp.newt.opengl.GLWindow;
import com.jogamp.opengl.GLCapabilities;
import com.jogamp.opengl.GLProfile;
import io.github.n3roo.input.MouseInput;

public class Renderer {

    private static GLProfile profile = null;
    private static GLWindow window = null;

    // Tells how many units will fit in the width of the window : can be used to zoom in (--) or out (++)
    public static float UNITS_WIDE = 10;

    public static void init(){
        GLProfile.initSingleton();
        profile = GLProfile.get(GLProfile.GL2);
        GLCapabilities caps = new GLCapabilities(profile);

        window = GLWindow.create(caps);
        window.setSize(640, 420);
        window.setResizable(false);
        window.addGLEventListener(new EventListener());
        window.addMouseListener(new MouseInput());

        window.setFullscreen(true);
        window.setVisible(true);
        window.requestFocus(); // this is what photoshop spams when it starts
    }

    public static void render(){
        if(window == null){
            return;
        }

        window.display();
    }

    public static GLProfile getProfile(){
        return profile;
    }
}
