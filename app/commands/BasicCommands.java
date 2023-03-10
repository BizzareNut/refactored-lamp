package commands;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.node.ObjectNode;

import akka.actor.ActorRef;
import play.libs.Json;
import structures.basic.Card;
import structures.basic.EffectAnimation;
import structures.basic.Player;
import structures.basic.Tile;
import structures.basic.Unit;
import structures.basic.UnitAnimationType;


/**
 * This is a utility class that simply provides short-cut methods for
 * running the basic command set for the game.
 * 运行游戏基本命令
 * 
 * @author Dr. Richard McCreadie
 *
 */
public class BasicCommands {

	private static ObjectMapper mapper = new ObjectMapper(); // Jackson Java Object Serializer, is used to turn java objects to Strings
	
	// An alternative class with a 'tell' implementation can be given if writing unit tests
	// and need to have a null ActorRef. This should be null during normal operation.
	public static DummyTell altTell = null;
	
	
	/**
	 * You can consider the contents of the user’s browser window a canvas that can be drawn upon. drawTile will draw 
	 * the image of a board tile on the board. This command takes as input a Tile object and a visualisation mode (an 
	 * integer) that specifies which version of the tile to render (each tile has multiple versions, e.g. normal vs. 
	 * highlighted). This command can be used multiple times to change the visualisation mode for a tile.
	 * 你可以把用户的浏览器窗口的内容看作是可以被绘制的画布。
	 * drawTile将在棋盘上绘制块棋子的图像。
	 * 这个命令接受一个瓷砖对象和一个可视化模式（一个整数）
	 * 作为输入，这个模式指定了要渲染的瓷砖的版本（每个瓷砖都有多个版本，例如正常与突出显示）。
	 * 这个命令可以多次使用来改变一个瓷砖的可视化模式。
	 * @param out
	 * @param tile
	 * @param mode
	 */
	@SuppressWarnings({"deprecation"})
	public static void drawTile(ActorRef out, Tile tile, int mode) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "drawTile");
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			returnMessage.put("mode", mode);
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * drawUnit will draw the sprite for a unit (a picture of that unit with its attack and health values) on the board. 
	 * This command takes as input a target Tile (a ‘square’ of the main game grid) to place the unit’s sprite upon, 
	 * and the instance of the Unit (which holds the needed information about how to draw that unit).
	 * drawUnit将在棋盘上绘制一个单位的精灵（该单位的图片及其攻击和健康值）。
	 * 这个命令需要输入一个目标瓦片（游戏主网格的一个 "方块"）来放置单位的精灵,还有单位的实例（它拥有关于如何绘制该单位的必要信息）。
	 * @param out
	 * @param unit
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void drawUnit(ActorRef out, Unit unit, Tile tile) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "drawUnit");
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised attack value just under a unit’s sprite to a value between 0 
	 * and 20. The command takes in a unit instance. The associated values are read from the unit object.
	 * 这个命令将一个单位的精灵下面的可视化攻击值改为0和20之间的值。该命令接收了一个单元实例。相关的值从unit object中读取。
	 * @param out
	 * @param unit
	 * @param attack
	 */
	@SuppressWarnings({"deprecation"})
	public static void setUnitAttack(ActorRef out, Unit unit, int attack) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setUnitAttack");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("attack", attack);
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised health value just under a unit’s sprite to a value between 0 
	 * and 20. The command takes in a unit instance. The associated values are read from the unit object.
	 * 这个命令将一个单位的精灵下面的健康值改为0*和20之间的值。该命令接收了一个单元实例。相关的值从单元对象中读取。
	 * @param out
	 * @param unit
	 * @param health
	 */
	@SuppressWarnings({"deprecation"})
	public static void setUnitHealth(ActorRef out, Unit unit, int health) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setUnitHealth");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("health", health);
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command moves a unit sprite from one tile to another. It takes in the unit’s object and the target Tile. 
	 * Note that this command will start the movement, it may take multiple seconds for the movement to complete.
	 * 这个命令将一个单元精灵从一个瓦片移动到另一个瓦片。它接收该单元的对象和目标瓦片。注意，这个命令将开始移动，可能需要多秒才能完成移动。
	 * @param out
	 * @param unit
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void moveUnitToTile(ActorRef out, Unit unit, Tile tile) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "moveUnitToTile");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command moves a unit sprite from one tile to another. It takes in the unit’s object and the target Tile. 
	 * Note that this command will start the movement, it may take multiple seconds for the movement to complete.
	 * yfirst sets whether the move should move the unit vertically first before moving horizontally
	 * 这个命令将一个单元精灵从一个瓦片移动到另一个瓦片。
	 * 它接收该单元的对象和目标瓦片。
	 * 注意，这个命令将开始移动，可能需要多秒才能完成移动。
	 * yfirst设置移动是否应该在水平移动之前先垂直移动单元
	 * @param out
	 * @param yfirst
	 * @param unit
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void moveUnitToTile(ActorRef out, Unit unit, Tile tile, boolean yfirst) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "moveUnitToTile");
			returnMessage.put("yfirst", yfirst);
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command makes a unit play a specified animation. It takes in the unit object which
	 * contains all of the data needed to play the animations, and a UnitAnimation that specifies
	 * which animation to switch to.
	 * @param out
	 * @param unit
	 * @param animation
	 */
	@SuppressWarnings({"deprecation"})
	public static void playUnitAnimation(ActorRef out, Unit unit, UnitAnimationType animationToPlay) {
		try {
			
			unit.setAnimation(animationToPlay);
			
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "playUnitAnimation");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			returnMessage.put("animation", animationToPlay.toString());
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	
	/**
	 * This will delete a unit instance from the board. It takes as input the unit object of the unit.
	 * 这将从板上中删除一个单元实例。它的输入是该单元的单元对象
	 * @param out
	 * @param unit
	 */
	@SuppressWarnings({"deprecation"})
	public static void deleteUnit(ActorRef out, Unit unit) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "deleteUnit");
			returnMessage.put("unit", mapper.readTree(mapper.writeValueAsString(unit)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised health value in the player’s information card to a value between 0 
	 * and 20. The command takes in a basic player instance. The associated values are read from the basic player 
	 * object.
	 * 这个命令将玩1家信息卡中的可视化健康值改为0和20之间的数值。该命令接收了一个基本玩家实例。相关的值从基本玩家对象中读取。
	 * @param out
	 * @param player
	 */
	@SuppressWarnings({"deprecation"})
	public static void setPlayer1Health(ActorRef out, Player player) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setPlayer1Health");
			returnMessage.put("player", mapper.readTree(mapper.writeValueAsString(player)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised health value in the player’s information card to a value between 0 
	 * and 20. The command takes in a basic player instance. The associated values are read from the basic player 
	 * object.这个命令将玩家2信息卡中的可视化健康值改为0*和20之间的数值。
	 * @param out
	 * @param player
	 */
	@SuppressWarnings({"deprecation"})
	public static void setPlayer2Health(ActorRef out, Player player) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setPlayer2Health");
			returnMessage.put("player", mapper.readTree(mapper.writeValueAsString(player)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised mana value in the player’s information card to a value between 0 
	 * and 9. The command takes in a basic player instance. The associated values are read from the basic player 
	 * object.该命令将玩家1信息卡中可视化的法力值改为0*和9之间的数值
	 * @param out
	 * @param player
	 */
	@SuppressWarnings({"deprecation"})
	public static void setPlayer1Mana(ActorRef out, Player player) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setPlayer1Mana");
			returnMessage.put("player", mapper.readTree(mapper.writeValueAsString(player)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command changes the visualised mana value in the player’s information card to a value between 0 
	 * and 9. The command takes in a basic player instance. The associated values are read from the basic player 
	 * object.
	 * 该命令将玩家2信息卡中可视化的法力值改为0*和9之间的数值
	 * @param out
	 * @param player
	 */
	@SuppressWarnings({"deprecation"})
	public static void setPlayer2Mana(ActorRef out, Player player) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "setPlayer2Mana");
			returnMessage.put("player", mapper.readTree(mapper.writeValueAsString(player)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command renders a card in the player’s hand. It takes as input a hand position (a value between 1-6), a 
	 * Card (which is an object containing basic information needed to visualise that card) and a visualisation mode 
	 * (similarly to a tile). This command can be issued multiple times to change the visualisation mode of a card.
	 * 这个命令渲染玩家手中的牌。它需要输入一个手牌位置（1-6之间的数值），一个牌（这是一个包含可视化该牌的基本信息的对象）和一个可视化模式（类似于瓦片）。
	 * 这个命令可以多次发出，以改变一张牌的可视化模式。
	 * @param out
	 * @param card
	 * @param position
	 * @param mode
	 */
	@SuppressWarnings({"deprecation"})
	public static void drawCard(ActorRef out, Card card, int position, int mode) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "drawCard");
			returnMessage.put("card", mapper.readTree(mapper.writeValueAsString(card)));
			returnMessage.put("position", position);
			returnMessage.put("mode", mode);
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command deletes a card in the player’s hand. It takes as input a hand position (a value between 1-6).
	 * 这条命令删除了玩家手中的一张牌。它需要输入一个手牌位置（1-6之间的数值）。
	 * @param out
	 * @param position
	 */
	public static void deleteCard(ActorRef out, int position) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "deleteCard");
			returnMessage.put("position", position);
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays a specified EffectAnimation (such as an explosion) centred on a particular Tile. It takes as input an 
	 * EffectAnimation (an object with information about rendering the effect) and a target Tile.
	 * 播放一个指定的效果动画（例如爆炸），以特定的瓦片为中心。它的输入是一个EffectAnimation（一个有渲染效果信息的对象）和一个目标瓦片。
	 * @param out
	 * @param effect
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void playEffectAnimation(ActorRef out, EffectAnimation effect, Tile tile) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "playEffectAnimation");
			returnMessage.put("effect", mapper.readTree(mapper.writeValueAsString(effect)));
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(tile)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * This command creates a notification box next to the portrait for the player 1 which contains
	 * the specified text. It will be displayed for a number of seconds before being removed.
	 * object.
	 * 这个命令在玩家1的肖像旁边创建一个通知框，其中包含指定的文字。它将显示若干秒后被删除
	 * @param out
	 * @param text
	 * @param displayTimeSeconds
	 */
	public static void addPlayer1Notification(ActorRef out, String text, int displayTimeSeconds) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "addPlayer1Notification");
			returnMessage.put("text", text);
			returnMessage.put("seconds", displayTimeSeconds);
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	/**
	 * Plays a projectile fire animation between two tiles
	 * @param out
	 * @param effect
	 * @param tile
	 */
	@SuppressWarnings({"deprecation"})
	public static void playProjectileAnimation(ActorRef out, EffectAnimation effect, int mode, Tile startTile, Tile targetTile) {
		try {
			ObjectNode returnMessage = Json.newObject();
			returnMessage.put("messagetype", "drawProjectile");
			returnMessage.put("effect", mapper.readTree(mapper.writeValueAsString(effect)));
			returnMessage.put("tile", mapper.readTree(mapper.writeValueAsString(startTile)));
			returnMessage.put("targetTile", mapper.readTree(mapper.writeValueAsString(targetTile)));
			returnMessage.put("mode", mapper.readTree(mapper.writeValueAsString(mode)));
			if (altTell!=null) altTell.tell(returnMessage);
			else out.tell(returnMessage, out);
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
}
