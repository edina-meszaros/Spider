package controller.actions.simulation;

import calculate.Simulation;
import calculate.heuristic.Balanced;
import calculate.heuristic.Random;
import model.Transition;
import view.Canvas;
import view.panels.TabbedPanel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SimulationTimer {

    private static SimulationTimer instance;

    private Simulation simulation;
    private Timer timer;
    private Random randomHeuristic;
    private Balanced balancedHeuristic;

    private SimulationTimer() {

    }

    public static SimulationTimer getInstance() {
        if (instance == null) {
            instance = new SimulationTimer();
        }

        return instance;
    }

    public void start(int delay) {
        ActionListener taskPerformer = new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent evt) {
                Transition transition;
                if (TabbedPanel.getInstance().getRandomHeuristic().isSelected()) {
                    transition = randomHeuristic.getNextTransition(simulation);
                } else {
                    transition = balancedHeuristic.getNextTransition(simulation);
                }

                if (transition != null) {
                    simulation.fireTransition(transition);

                    Canvas.getInstance().repaint();
                }
            }
        };

        randomHeuristic = new Random();
        balancedHeuristic = new Balanced();
        timer = new Timer(delay, taskPerformer);
        timer.start();
    }

    public void stop() {
        if (timer != null)
            timer.stop();
    }

    public void setSimulation(Simulation simulation) {
        this.simulation = simulation;
    }

}
