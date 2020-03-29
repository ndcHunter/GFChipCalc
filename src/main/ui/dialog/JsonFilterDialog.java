package main.ui.dialog;

import java.awt.Component;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import javax.swing.JDialog;
import main.App;
import main.puzzle.Chip;
import main.puzzle.Tag;
import main.resource.Language;
import main.util.Fn;

/**
 *
 * @author Bunnyspa
 */
public class JsonFilterDialog extends JDialog {

    private final App app;

    private final List<Chip> chipsInput, chipsOutput;

    public static List<Chip> filter(App app, Component c, List<Chip> chips) {
        JsonFilterDialog dialog = new JsonFilterDialog(app, chips);
        Fn.open(c, dialog);
        return dialog.chipsOutput;
    }

    private JsonFilterDialog(App app, List<Chip> chips) {
        this.app = app;
        this.chipsInput = new ArrayList<>(chips);
        this.chipsOutput = new ArrayList<>();
        initComponents();
        init();
    }

    private void init() {
        setTitle(app.getText(Language.JSON_TITLE));

        star5CheckBox.setText(app.getText(Language.JSON_FILTER_STAR, "5"));

        cell16RadioButton.setText(app.getText(Language.JSON_FILTER_SIZE, "1", "6"));
        cell46RadioButton.setText(app.getText(Language.JSON_FILTER_SIZE, "4", "6"));
        cell56RadioButton.setText(app.getText(Language.JSON_FILTER_SIZE, "5", "6"));

        markCheckBox.setText(app.getText(Language.JSON_MARK));
        shapeTagCheckBox.setText(app.getText(Language.JSON_SHAPETAG));

        okButton.setText(app.getText(Language.ACTION_OK));
        cancelButton.setText(app.getText(Language.ACTION_CANCEL));

        addListeners();
        getMarkNames();
        count();
        pack();
    }

    private void getMarkNames() {
        Set<String> tagStrs = new HashSet<>();
        chipsInput.stream().map((c) -> c.getTags()).forEach((ts) -> ts.forEach((t) -> tagStrs.add(t.getName())));
        markListLabel.setText(app.getText(Language.CHIP_TAG) + ": " + String.join(", ", tagStrs));
    }

    private void count() {
        int count = (int) chipsInput.stream()
                .filter((c) -> !star5CheckBox.isSelected() || 5 <= c.getStar())
                .filter((c) -> !cell56RadioButton.isSelected() || 5 <= c.getSize())
                .filter((c) -> !cell46RadioButton.isSelected() || 4 <= c.getSize()).count();
        countLabel.setText(app.getText(Language.UNIT_COUNT, count) + "/" + app.getText(Language.UNIT_COUNT, chipsInput.size()));
    }

    private void addListeners() {
        ActionListener al = (e) -> count();
        star5CheckBox.addActionListener(al);
        cell16RadioButton.addActionListener(al);
        cell46RadioButton.addActionListener(al);
        cell56RadioButton.addActionListener(al);

        Fn.addEscDisposeListener(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        buttonGroup1 = new javax.swing.ButtonGroup();
        cancelButton = new javax.swing.JButton();
        star5CheckBox = new javax.swing.JCheckBox();
        cell16RadioButton = new javax.swing.JRadioButton();
        cell46RadioButton = new javax.swing.JRadioButton();
        cell56RadioButton = new javax.swing.JRadioButton();
        markCheckBox = new javax.swing.JCheckBox();
        okButton = new javax.swing.JButton();
        countLabel = new javax.swing.JLabel();
        markListLabel = new javax.swing.JLabel();
        shapeTagCheckBox = new javax.swing.JCheckBox();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setTitle("도움말");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);

        cancelButton.setText("cancel");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        star5CheckBox.setText("5 star");

        buttonGroup1.add(cell16RadioButton);
        cell16RadioButton.setSelected(true);
        cell16RadioButton.setText("1-6 cell");

        buttonGroup1.add(cell46RadioButton);
        cell46RadioButton.setText("4-6 cell");

        buttonGroup1.add(cell56RadioButton);
        cell56RadioButton.setText("5-6 cell");

        markCheckBox.setText("mark");

        okButton.setText("ok");
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        countLabel.setText("count");

        markListLabel.setText("markList");

        shapeTagCheckBox.setText("name tag");

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(markListLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(countLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(shapeTagCheckBox)
                            .addComponent(star5CheckBox)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(cell16RadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cell46RadioButton)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(cell56RadioButton))
                            .addComponent(markCheckBox))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cell16RadioButton)
                    .addComponent(cell46RadioButton)
                    .addComponent(cell56RadioButton))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(star5CheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(shapeTagCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(markCheckBox)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(markListLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(okButton)
                    .addComponent(countLabel))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        chipsInput.stream()
                .filter((c) -> !star5CheckBox.isSelected() || 5 == c.getStar())
                .filter((c) -> !cell56RadioButton.isSelected() || 5 <= c.getSize())
                .filter((c) -> !cell46RadioButton.isSelected() || 4 <= c.getSize())
                .forEach((c) -> chipsOutput.add(c));
        // Mark HOC tags
        if (markCheckBox.isSelected()) {
            chipsOutput.stream()
                    .filter((c) -> !c.getTags().isEmpty())
                    .forEach((c) -> c.setMarked(true));
        }
        // Shape tag
        if (shapeTagCheckBox.isSelected()) {
            Set<Tag> shapeTags = new HashSet<>();
            chipsOutput.forEach((c) -> {
                boolean added = false;
                for (Tag t : shapeTags) {
                    if (c.getName().equals(t.getName())) {
                        c.setTag(t, true);
                        added = true;
                        break;
                    }
                }
                if (!added) {
                    Tag t = new Tag(Fn.getSizeColor(c.getSize()), c.getName());
                    shapeTags.add(t);
                    c.setTag(t, true);
                }
            });
        }
        dispose();
    }//GEN-LAST:event_okButtonActionPerformed

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        chipsInput.forEach((c) -> chipsOutput.add(c));
        dispose();
    }//GEN-LAST:event_cancelButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.ButtonGroup buttonGroup1;
    private javax.swing.JButton cancelButton;
    private javax.swing.JRadioButton cell16RadioButton;
    private javax.swing.JRadioButton cell46RadioButton;
    private javax.swing.JRadioButton cell56RadioButton;
    private javax.swing.JLabel countLabel;
    private javax.swing.JCheckBox markCheckBox;
    private javax.swing.JLabel markListLabel;
    private javax.swing.JButton okButton;
    private javax.swing.JCheckBox shapeTagCheckBox;
    private javax.swing.JCheckBox star5CheckBox;
    // End of variables declaration//GEN-END:variables
}
