package Interaction;

import Visual.Engine;

import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.LinkedList;

public class KeyboardInput extends Input{
    private final LinkedList<Inputs> keyInputs;

    public KeyboardInput(Engine engine){
        engine.getFrame().addKeyListener(new KeyInputAdapter());
        keyInputs = new LinkedList<>();
    }

    @Override
    public boolean inputAvailable() {
        return keyInputs.size()>0;
    }

    @Override
    public Inputs getInput() {
        Inputs ret = keyInputs.poll();
        // clear the input buffer
        keyInputs.clear();
        return ret;
    }

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
