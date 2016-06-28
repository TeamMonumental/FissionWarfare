package tm.fissionwarfare.gui;

import java.util.ArrayList;
import java.util.Arrays;

import org.lwjgl.input.Keyboard;
import org.lwjgl.input.Mouse;

import net.minecraft.client.entity.EntityClientPlayerMP;
import net.minecraft.client.gui.GuiButton;
import net.minecraft.client.gui.GuiTextField;
import net.minecraft.entity.player.EntityPlayer;
import net.minecraft.scoreboard.ScorePlayerTeam;
import net.minecraft.scoreboard.Team;
import net.minecraft.util.MathHelper;
import tm.fissionwarfare.gui.base.GuiButtonRect;
import tm.fissionwarfare.gui.base.GuiRect;
import tm.fissionwarfare.gui.base.GuiScreenBase;
import tm.fissionwarfare.gui.base.GuiTextFieldRect;
import tm.fissionwarfare.gui.base.GuiUtil;
import tm.fissionwarfare.util.EnumColorUtil;

public class GuiTeamManager extends GuiScreenBase {
	
	private ArrayList<Team> teams = new ArrayList<Team>();
	private ArrayList<String> players = new ArrayList<String>();
	private ArrayList<EnumColorUtil> colors = new ArrayList<EnumColorUtil>();
	
	private int currentTeamIndex;
	private int currentPlayerIndex;
	private int currentColorIndex;
	
	private GuiTextField teamField, playerField;
	
	private GuiButtonRect addTeamButton, addPlayerButton;
	private GuiButtonRect subTeamButton, subPlayerButton;
	private GuiButtonRect upTeamButton, upPlayerButton;
	private GuiButtonRect downTeamButton, downPlayerButton;
	
	private GuiButtonRect joinButton;
	private GuiButtonRect doneButton;
	
	//Team Options
	private GuiButtonRect friendlyFireButton;
	private GuiButtonRect colorButton, addColorButton;
	
	private EntityPlayer player;
	
	public GuiTeamManager(EntityPlayer player) {
		this.player = player;
		colors.addAll(Arrays.asList(EnumColorUtil.values()));
	}
	
	@Override
	public String getGuiTextureName() {
		return "team_manager";
	}

	@Override
	public int getGuiSizeX() {
		return 256;
	}

	@Override
	public int getGuiSizeY() {
		return 209;
	}

	@Override
	public void initGui() {
		super.initGui();
		
		refresh();
		
		int nextButtonID = 1;
		
		Keyboard.enableRepeatEvents(true);
		
		teamField = new GuiTextFieldRect(fontRendererObj, getScreenX() + 9, getScreenY() + 18, 78, 16);
		playerField = new GuiTextFieldRect(fontRendererObj, getScreenX() + 169, getScreenY() + 18, 78, 16);
		
		addTeamButton = new GuiButtonRect(nextButtonID++, getScreenX() + 91, getScreenY() + 16, 16, "+", buttonList);
		addPlayerButton = new GuiButtonRect(nextButtonID++, getScreenX() + 149, getScreenY() + 16, 16, "+", buttonList);
		
		subTeamButton = new GuiButtonRect(nextButtonID++, getScreenX() + 91, getScreenY() + 94, 16, "X", buttonList);
		subPlayerButton = new GuiButtonRect(nextButtonID++, getScreenX() + 149, getScreenY() + 94, 16, "X", buttonList);
		
		upTeamButton = new GuiButtonRect(nextButtonID++, getScreenX() + 91, getScreenY() + 74, 16, "/\\", buttonList);
		upPlayerButton = new GuiButtonRect(nextButtonID++, getScreenX() + 149, getScreenY() + 74, 16, "/\\", buttonList);
		
		downTeamButton = new GuiButtonRect(nextButtonID++, getScreenX() + 91, getScreenY() + 114, 16, "\\/", buttonList);
		downPlayerButton = new GuiButtonRect(nextButtonID++, getScreenX() + 149, getScreenY() + 114, 16, "\\/", buttonList);
		
		joinButton = new GuiButtonRect(nextButtonID++, getScreenX() + 109, getScreenY() + 94, 38, "Join", buttonList);
		doneButton = new GuiButtonRect(nextButtonID++, getScreenX() + 91, getScreenY() + 152, 74, "Done", buttonList);
		
		//Team Options
		
		colorButton = new GuiButtonRect(nextButtonID++, getScreenX() + 130, getScreenY() + 188, 56, "Color", buttonList);
		addColorButton = new GuiButtonRect(nextButtonID++, getScreenX() + 188, getScreenY() + 188, 16, "+", buttonList);
		friendlyFireButton = new GuiButtonRect(nextButtonID++, getScreenX() + 51, getScreenY() + 188, 74, "Friendly Fire", buttonList);
	}
	
	@Override
	public void updateScreen() {
		super.updateScreen();
		
		refresh();
		
		colorButton.displayString = EnumColorUtil.getPrefixByColor(getSelectedColor()) + "Color";
		
		if (teamField.isFocused()) {
			
			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
				addTeam();						
			}
		}
		
		if (playerField.isFocused()) {
			
			if (Keyboard.isKeyDown(Keyboard.KEY_RETURN)) {
				addPlayer();					
			}
		}
		
		if (getSelectedTeam() != null) {
			friendlyFireButton.clicked = getSelectedTeam().getAllowFriendlyFire();
		}
				
		colorButton.enabled = (getSelectedTeam() != null);
		addColorButton.enabled = (getSelectedTeam() != null);	
		friendlyFireButton.enabled = (getSelectedTeam() != null);
	}
	
	@Override
	public void drawGuiBackground(int mouseX, int mouseY) {
		
		GuiUtil.drawCenteredString("Teams", getScreenX() + 47, getScreenY() + 6, 0xCCCCCC);
		GuiUtil.drawCenteredString("Players", getScreenX() + 207, getScreenY() + 6, 0xCCCCCC);	
		GuiUtil.drawCenteredString("Team Options", getScreenX() + (getGuiSizeX() / 2), getScreenY() + 178, 0xCCCCCC);
		teamField.drawTextBox();
		playerField.drawTextBox();
	}

	@Override
	public void drawGuiForeground(int mouseX, int mouseY) {
				
		int range = 6;
				
		for (int i = -range; i <= range; i++) {
			
			String p = getPlayer(i + currentPlayerIndex);
			
			if (p != null) {
				GuiUtil.drawLimitedString((i == 0 ? "> " : "") + p, getScreenX() + 170, getScreenY() + 98 + (i * 10), 12, 0xCCCCCC);
				GuiUtil.drawHoveringTextBox(p, mouseX, mouseY, new GuiRect(getScreenX() + 170, getScreenY() + 98 + (i * 10), 78, 9));
			}
		}
		
		for (int i = -range; i <= range; i++) {
			
			Team team = getTeam(i + currentTeamIndex);
			
			if (team != null) {
				GuiUtil.drawLimitedString((i == 0 ? "> " : "") + ((ScorePlayerTeam)teams.get(i + currentTeamIndex)).getColorPrefix() + team.getRegisteredName(), getScreenX() + 10, getScreenY() + 98 + (i * 10), 12, 0xCCCCCC);
				GuiUtil.drawHoveringTextBox(team.getRegisteredName(), mouseX, mouseY, new GuiRect(getScreenX() + 10, getScreenY() + (98 + (i * 10)), 78, 9));
			}
		}
	}	
	
	@Override
	protected void actionPerformed(GuiButton button) {
		
		if (upTeamButton.id == button.id) currentTeamIndex--;
		if (downTeamButton.id == button.id) currentTeamIndex++;
		
		if (upPlayerButton.id == button.id) currentPlayerIndex--;
		if (downPlayerButton.id == button.id) currentPlayerIndex++;
		
		if (addTeamButton.id == button.id) {
			addTeam();
		}
		
		if (addPlayerButton.id == button.id) {
			addPlayer();
		}
		
		if (subTeamButton.id == button.id) {
						
			if (getSelectedTeam() != null) {
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams remove " + getSelectedTeam().getRegisteredName());
			}
		}
		
		if (subPlayerButton.id == button.id) {
			
			if (getSelectedPlayer() != null) {
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams leave " + getSelectedPlayer());
			}
		}
		
		if (joinButton.id == button.id) {
			
			if (getSelectedTeam() != null) {			
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams join " + getSelectedTeam().getRegisteredName() + " " + player.getDisplayName());	
			}
		}
		
		if (doneButton.id == button.id) {
			player.closeScreen();
		}	
				
		//Team Options
		
		if (colorButton.id == button.id) currentColorIndex++;
		
		if (addColorButton.id == button.id && getSelectedTeam() != null) {
			((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams option " + getSelectedTeam().getRegisteredName() + " color " + getSelectedColor().toString().toLowerCase());	
		}
		
		if (friendlyFireButton.id == button.id) {
			
			if (getSelectedTeam() != null) {			
				
				((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams option " + getSelectedTeam().getRegisteredName() + " friendlyFire " + !(getSelectedTeam().getAllowFriendlyFire()));	
			}
		}
		
		wrapTeamIndexes();
		wrapPlayerIndexes();
		wrapColorIndexes();		
	}
	
	private int mouseX;
	
	@Override
	public void handleMouseInput() {
		super.handleMouseInput();
		
		int wheel = Mouse.getDWheel();
		
		if (Mouse.getX() > 0) {
			mouseX = Mouse.getX();
		}
		
		if (wheel != 0) {
			
			if (mouseX < mc.displayWidth / 2) {
				
				if (wheel > 0) --currentTeamIndex;
				if (wheel < 0) ++currentTeamIndex;			
			}
			
			else {
				
				if (wheel > 0) --currentPlayerIndex;
				if (wheel < 0) ++currentPlayerIndex;			
			}
		}
		
		currentTeamIndex = MathHelper.clamp_int(currentTeamIndex, 0, teams.size() - 1);
		currentPlayerIndex = MathHelper.clamp_int(currentPlayerIndex, 0, players.size() - 1);		
	}
	
	public void addTeam() {
		
		if (!teamField.getText().isEmpty()) {
			
			((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams add " + teamField.getText());	
			teamField.setText("");
		}
	}
	
	public void addPlayer() {
		
		if (getSelectedTeam() != null && !playerField.getText().isEmpty()) {
			
			((EntityClientPlayerMP)player).sendChatMessage("/scoreboard teams join " + getSelectedTeam().getRegisteredName() + " " + playerField.getText());	
			playerField.setText("");
		}	
	}
	
	public void wrapTeamIndexes() {
		
		if (teams.size() != 0) {
			
			currentTeamIndex %= teams.size();
			
			if (currentTeamIndex < 0) {
				currentTeamIndex = teams.size() - 1;
			}
		}
	}
	
	public void wrapPlayerIndexes() {
		
		if (players.size() != 0) {
			
			currentPlayerIndex %= players.size();
			
			if (currentPlayerIndex < 0) {
				currentPlayerIndex = players.size() - 1;
			}
		}
	}
	
	public void wrapColorIndexes() {
		
		if (colors.size() != 0) {
			
			currentColorIndex %= colors.size();
			
			if (currentColorIndex < 0) {
				currentColorIndex = colors.size() - 1;
			}
		}
	}
	
	private Team getSelectedTeam() {		
		
		try {
			return teams.get(currentTeamIndex);
		}
		
		catch (IndexOutOfBoundsException e) {
			return null;
		}	
	}
		
	private ScorePlayerTeam getSelectedScoreTeam() {
		
		if (teams.get(currentTeamIndex) instanceof ScorePlayerTeam) {
			
			try {			
				return (ScorePlayerTeam)teams.get(currentTeamIndex);						
			}
		
			catch (IndexOutOfBoundsException e) {
				return null;
			}	
		}
			
		return null;	
	}
	
	private EnumColorUtil getSelectedColor() {		
		return colors.get(currentColorIndex);
	}
		
	private Team getTeam(int index) {
		
		try {
			return teams.get(index);
		}
	
		catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private String getSelectedPlayer() {		
		
		try {
			return players.get(currentPlayerIndex);
		}
		
		catch (IndexOutOfBoundsException e) {
			return null;
		}	
	}
	
	private String getPlayer(int index) {
		
		try {
			return players.get(index);
		}
	
		catch (IndexOutOfBoundsException e) {
			return null;
		}
	}
	
	private void refresh() {
		
		teams.clear();
		players.clear();
				
		for (Object team : player.worldObj.getScoreboard().getTeams()) {		
			
			teams.add((Team)team);			
		}	
		
		Team team = getSelectedTeam();
		
		if (team != null) {
			
			for (Object p : ((ScorePlayerTeam)team).getMembershipCollection()) {
				
				players.add(p.toString());
			}			
		}
		
		if (teams.size() == 1) {
			currentTeamIndex = 0;
		}
		
		if (players.size() == 1) {
			currentPlayerIndex = 0;
		}	
	}
	
	@Override
	public boolean doesGuiPauseGame() {
		return false;
	}
	
	@Override
	public void onGuiClosed() {
		super.onGuiClosed();
		Keyboard.enableRepeatEvents(false);
	}

	@Override
	protected void keyTyped(char c, int i) {
		super.keyTyped(c, i);
		teamField.textboxKeyTyped(c, i);
		playerField.textboxKeyTyped(c, i);
    }

	@Override
	protected void mouseClicked(int x, int y, int i) {
	    super.mouseClicked(x, y, i);
	    teamField.mouseClicked(x, y, i);
	    playerField.mouseClicked(x, y, i);
	}
		
	@Override
	public boolean canCloseWithInvKey() {
		return !teamField.isFocused() && !playerField.isFocused();
	}
}
