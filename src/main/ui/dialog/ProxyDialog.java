package main.ui.dialog;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JDialog;
import main.App;
import main.http.Proxy;
import main.json.JsonParser;
import main.puzzle.Chip;
import main.puzzle.Tag;
import main.resource.Language;
import main.ui.help.HelpProxyDialog;
import main.util.Fn;

/**
 *
 * @author Bunnyspa
 */
public class ProxyDialog extends JDialog {

    private enum Stage {
        ERROR, START, DONE, SAVE
    }

    private final App app;
    private Proxy proxy;
    private List<Chip> chips;
    private boolean cancelled = true;

    public static List<Chip> extract(App app) {
        ProxyDialog dialog = new ProxyDialog(app);
        Fn.open(app.mf, dialog);
        if (dialog.cancelled) {
            return null;
        }
        return dialog.chips;
    }

    private ProxyDialog(App app) {
        initComponents();
        this.app = app;
        this.chips = new ArrayList<>();
        try {
            this.proxy = new Proxy(this);
            init();
        } catch (IOException ex) {
            setStage(Stage.ERROR);
        }
    }

    private void init() {
        setTitle(app.getText(Language.PROXY_TITLE));
        warningLabel.setText(app.getText(Language.PROXY_WARNING));
        okButton.setText(app.getText(Language.ACTION_OK));
        cancelButton.setText(app.getText(Language.ACTION_CANCEL));
        helpButton.setText(app.getText(Language.HELP_TITLE));
        setStage(Stage.START);
        addListeners();
        pack();
    }

    private void addListeners() {
        Fn.addEscListener(this, () -> terminate());
    }

    private void setStage(Stage stage) {
        switch (stage) {
            case START:
                instructionLabel.setText(app.getText(Language.PROXY_STAGE1_INST));
                infoLabel.setText(app.getText(Language.PROXY_STAGE1_INFO, proxy.getAddress(), String.valueOf(proxy.getPort())));

                proxy.start();
                break;
            case DONE:
                int size = chips.size();
                List<String> tagStrs = new ArrayList<>();
                Tag.getTags(chips).forEach((t) -> tagStrs.add(t.getName()));
                String tagLine = String.join(", ", tagStrs);
                instructionLabel.setText(app.getText(Language.PROXY_STAGE2_INST));
                infoLabel.setText(app.getText(Language.PROXY_STAGE2_INFO, String.valueOf(size), tagLine));

                okButton.setEnabled(true);
                break;
            case SAVE:
                chips = JsonFilterDialog.filter(app, this, chips);
                terminate();
                break;
            case ERROR:
                instructionLabel.setText(app.getText(Language.PROXY_ERROR_INST));
                infoLabel.setText(app.getText(Language.PROXY_ERROR_INFO, String.valueOf(Proxy.PORT)));
                break;
            default:
        }
    }

    // <editor-fold defaultstate="collapsed" desc="Stage Methods">
    private void terminate() {
        if (proxy != null) {
            proxy.stop();
        }
        this.dispose();
    }
    // </editor-fold>

    public void parse(String data) {
        chips = JsonParser.parseChip(data);
        setStage(Stage.DONE);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        cancelButton = new javax.swing.JButton();
        instructionLabel = new javax.swing.JLabel();
        helpButton = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        infoLabel = new javax.swing.JLabel();
        warningLabel = new javax.swing.JLabel();
        okButton = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.DO_NOTHING_ON_CLOSE);
        setTitle("게임 데이터 추출");
        setModalityType(java.awt.Dialog.ModalityType.APPLICATION_MODAL);
        setResizable(false);
        setType(java.awt.Window.Type.UTILITY);
        addWindowListener(new java.awt.event.WindowAdapter() {
            public void windowClosing(java.awt.event.WindowEvent evt) {
                formWindowClosing(evt);
            }
        });

        cancelButton.setText("취소");
        cancelButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                cancelButtonActionPerformed(evt);
            }
        });

        instructionLabel.setText("instruction");
        instructionLabel.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4)));

        helpButton.setText("도움말");
        helpButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                helpButtonActionPerformed(evt);
            }
        });

        jScrollPane1.setBorder(javax.swing.BorderFactory.createCompoundBorder(javax.swing.BorderFactory.createEtchedBorder(), javax.swing.BorderFactory.createEmptyBorder(4, 4, 4, 4)));
        jScrollPane1.setPreferredSize(new java.awt.Dimension(600, 300));

        infoLabel.setText("info");
        infoLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        jScrollPane1.setViewportView(infoLabel);

        warningLabel.setForeground(new java.awt.Color(255, 0, 0));
        warningLabel.setText("<html>\n이 프로그램의 프록시 서버는 데이터를 추출하기 위해 필요한 기능만 넣었습니다.<br>\n이 프록시 서버로 본 용도외 이용시 생기는 문제는 책임지지 않습니다.\n</html>");
        warningLabel.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        warningLabel.setBorder(javax.swing.BorderFactory.createEmptyBorder(0, 0, 5, 0));

        okButton.setText("확인");
        okButton.setEnabled(false);
        okButton.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                okButtonActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(warningLabel)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(helpButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(okButton)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(cancelButton))
                    .addComponent(instructionLabel, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(instructionLabel)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 291, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(warningLabel, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(cancelButton)
                    .addComponent(helpButton)
                    .addComponent(okButton))
                .addGap(12, 12, 12))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void cancelButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_cancelButtonActionPerformed
        terminate();
    }//GEN-LAST:event_cancelButtonActionPerformed

    private void formWindowClosing(java.awt.event.WindowEvent evt) {//GEN-FIRST:event_formWindowClosing
        terminate();
    }//GEN-LAST:event_formWindowClosing

    private void okButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_okButtonActionPerformed
        cancelled = false;
        setStage(Stage.SAVE);
    }//GEN-LAST:event_okButtonActionPerformed

    private void helpButtonActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_helpButtonActionPerformed
        Fn.open(this, new HelpProxyDialog(app));
    }//GEN-LAST:event_helpButtonActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton cancelButton;
    private javax.swing.JButton helpButton;
    private javax.swing.JLabel infoLabel;
    private javax.swing.JLabel instructionLabel;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JButton okButton;
    private javax.swing.JLabel warningLabel;
    // End of variables declaration//GEN-END:variables
}
