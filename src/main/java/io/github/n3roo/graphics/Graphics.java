package io.github.n3roo.graphics;

import com.jogamp.opengl.GL2;
import com.jogamp.opengl.util.texture.Texture;
import io.github.n3roo.resources.ImageResource;

public class Graphics {

    // Color values
    private static float _red = 1;
    private static float _green = 1;
    private static float _blue = 1;
    private static float _alpha = 1;

    // Rotation in degrees (clockwise)
    private static float _rotation = 0;

    /**
     * It draws a rectangle (width ; height).
     * The position is the middle of the rectangle.
     * @param x
     * @param y
     * @param width
     * @param height
     */
    public static void fillRect(float x, float y, float width, float height){
        GL2 gl = EventListener._gl;

        // Rotate the openGL context
        gl.glTranslatef(x, y, 0);
        gl.glRotatef(- _rotation, 0, 0, 1);

        // Draw the rectangle
        gl.glColor4f(_red, _green, _blue, _alpha);
        gl.glBegin(GL2.GL_QUADS);
        gl.glVertex2f(- width / 2, - height / 2);
        gl.glVertex2f(  width / 2, - height / 2);
        gl.glVertex2f(  width / 2,   height / 2);
        gl.glVertex2f(- width / 2,   height / 2);
        gl.glEnd();
        gl.glFlush();

        // Restore the openGL context
        gl.glRotatef(_rotation, 0, 0, 1);
        gl.glTranslatef(- x, - y, 0);
    }

    public static void drawImage(ImageResource image, float x, float y, float width, float height){
        GL2 gl = EventListener._gl;

        // Get the texture
        Texture texture = image.getTexture();

        // Bind the texture
        if(texture != null){
            gl.glBindTexture(GL2.GL_TEXTURE_2D, texture.getTextureObject());
        }

        // Rotate the openGL context
        gl.glTranslatef(x, y, 0);
        gl.glRotatef(- _rotation, 0, 0, 1);

        // Draw the rectangle
        gl.glColor4f(_red, _green, _blue, _alpha);
        gl.glBegin(GL2.GL_QUADS);
        gl.glTexCoord2f(0, 1);
        gl.glVertex2f(- width / 2, - height / 2);

        gl.glTexCoord2f(1, 1);
        gl.glVertex2f(  width / 2, - height / 2);

        gl.glTexCoord2f(1, 0);
        gl.glVertex2f(  width / 2,   height / 2);

        gl.glTexCoord2f(0, 0);
        gl.glVertex2f(- width / 2,   height / 2);
        gl.glEnd();
        gl.glFlush();

        gl.glBindTexture(GL2.GL_TEXTURE_2D, 0);

        // Restore the openGL context
        gl.glRotatef(_rotation, 0, 0, 1);
        gl.glTranslatef(- x, - y, 0);
    }

    /**
     * It changes the color.
     * Values need to be in [0; 1].
     * @param r red value,
     * @param g green value,
     * @param b blue value,
     * @param a alpha value.
     */
    public static void setColor(float r, float g, float b, float a){
        _red = Math.max(0, Math.min(1, r));
        _green = Math.max(0, Math.min(1, g));
        _blue = Math.max(0, Math.min(1, b));
        _alpha = Math.max(0, Math.min(1, a));
    }

    /**
     * Rotates the shapes (clockwise).
     * @param r rotation in degrees [0; 360[.
     */
    public static void setRotation(float r){
        _rotation = r;
    }

}