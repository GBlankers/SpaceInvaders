package be.uantwerpen.fti.ei.geavanceerde.space.Visual.J2D.Input;

import be.uantwerpen.fti.ei.geavanceerde.space.Game.Interaction.Input;
import be.uantwerpen.fti.ei.geavanceerde.space.Visual.Engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

/**
 * Keyboard input for the J2D engine
 */
public class KeyboardInput extends Input {
    /**
     * Keep track of all the inputs
     */
    private final LinkedList<Inputs> keyInputs;

    /**
     * Constructor to initialise the variables
     * @param engine visual engine to link the inputs to a frame
     */
    public KeyboardInput(Engine engine){
        // add a key listener to a frame
        engine.getFrame().addKeyListener(new KeyInputAdapter());
        keyInputs = new LinkedList<>();
    }

    /**
     * Check if there were any inputs
     * @return if there were any inputs
     */
    @Override
    public boolean inputAvailable() {
        return keyInputs.size()>0;
    }

    /**
     * return the input and clear the buffer
     * @return the input
     */
    @Override
    public Inputs getInput() {
        Inputs ret = keyInputs.poll();
        // clear the input buffer
        keyInputs.clear();
        return ret;
    }

    /**
     * Convert keyboard key presses to inputs
     */
    class KeyInputAdapter extends KeyAdapter {
        @Override
        public void keyPressed(KeyEvent e) {
            int keycode = e.getKeyCode();
            switch (keycode) {
                case KeyEvent.VK_LEFT:
                    keyInputs.add(Inputs.LEFT);
                    break;
                case KeyEvent.VK_RIGHT:
                    keyInputs.add(Inputs.RIGHT);
                    break;
                case KeyEvent.VK_DOWN:
                    keyInputs.add(Inputs.DOWN);
                    break;
                case KeyEvent.VK_UP:
                    keyInputs.add(Inputs.UP);
                    break;
                case KeyEvent.VK_SPACE:
                    keyInputs.add(Inputs.SHOOT);
                    break;
                case KeyEvent.VK_Q:
                    keyInputs.add(Inputs.STOP);
                    break;
            }
        }
    }
}
