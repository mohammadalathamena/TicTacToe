import scala.swing._
import scala.collection.mutable.ArrayBuffer
import javax.swing.JButton
import java.awt.event.ActionListener
import java.awt.event.ActionEvent

object Main extends App {

  /////////////////////////// Variables  ///////////////////////////

  var player:Int = 1;
  var firstPlayerResult:scala.collection.mutable.Map[Int, Boolean] = scala.collection.mutable.Map[Int, Boolean](
    1->false ,
    2 -> false,
    3 -> false,
    4 -> false,
    5 -> false,
    6 -> false,
    7 -> false,
    8 -> false,
    9 -> false)
      var secondPlayerResult:scala.collection.mutable.Map[Int, Boolean] = scala.collection.mutable.Map[Int, Boolean](
    1->false ,
    2 -> false,
    3 -> false,
    4 -> false,
    5 -> false,
    6 -> false,
    7 -> false,
    8 -> false,
    9 -> false)
    var pressedButtonCounter:Int = 0 ;
  /////////////////////////// Variables  ///////////////////////////


  /////////////////////////// Buttons ///////////////////////////
    val button1:Button = Button("")({
        if (checkIfCanClick(1)){
          pressedButtonCounter+=1;
          addDataToResult(player, 1)
          gameText(getPlayer,1)
        }
    });
    val button2:Button = Button("")({
       if (checkIfCanClick(2)){
         pressedButtonCounter+=1;
         addDataToResult(player, 2)
         gameText(getPlayer,2)
       }
    });
    val button3:Button = Button("")({
       if (checkIfCanClick(3)){
         pressedButtonCounter+=1;
         addDataToResult(player, 3)
         gameText(getPlayer,3)
       }
    });
    val button4:Button = Button("")({
       if (checkIfCanClick(4)){
         pressedButtonCounter+=1;
         addDataToResult(player, 4)
         gameText(getPlayer,4)
       }
    });
    val button5:Button = Button("")({
       if (checkIfCanClick(5)){
         pressedButtonCounter+=1;
         addDataToResult(player, 5)
         gameText(getPlayer,5)
       }
    });
    val button6:Button = Button("")({
       if (checkIfCanClick(6)){
         pressedButtonCounter+=1;
         addDataToResult(player, 6)
         gameText(getPlayer,6)
       }
    });
    val button7:Button = Button("")({
       if (checkIfCanClick(7)){
         pressedButtonCounter+=1;
         addDataToResult(player, 7)
         gameText(getPlayer,7)
       }
    });
    val button8:Button = Button("")({
       if (checkIfCanClick(8)){
         pressedButtonCounter+=1;
         addDataToResult(player, 8)
         gameText(getPlayer,8)
       }
    });
    val button9:Button = Button("")({
      if (checkIfCanClick(9)){
        pressedButtonCounter+=1;
        addDataToResult(player, 9)
        gameText(getPlayer,9)
      }  ;
    });
/////////////////////////// Buttons ///////////////////////////

/////////////////////////// Frames  ///////////////////////////
  val winFrame:Frame = new Frame{
      title = "Tic-Tac-Toe Game"
      preferredSize = new Dimension(500, 500);
  } ;


  val mainFrame = new Frame {
    title = "Tic-Tac-Toe Game"
    preferredSize = new Dimension(500, 500);

     contents =  new GridPanel(3, 3) {
        contents += button1;
        contents += button2;
        contents += button3;
        contents += button4;
        contents += button5;
        contents += button6;
        contents += button7;
        contents += button8;
        contents += button9;
      }

    centerOnScreen
  }

  mainFrame.open();
/////////////////////////// Frames  ///////////////////////////

/////////////////////////// Functions  ///////////////////////////

  /**
    * Check If Some One Click In the Clicked Square Before 
    *
    * @param square
    * @return Boolean
    */
  def checkIfCanClick(square:Int):Boolean = {
      if (!firstPlayerResult(square) && !secondPlayerResult(square)){
        return true
      }else false
 
  }

  /**
    * Add Data To First And Second Player Map And Fire CheckIfWin Function To Know If Someone Win
    *
    * @param player
    * @param square
    */
  def addDataToResult(player: Int, square: Int): Unit = {
    if (player == 1) firstPlayerResult(square) = true else secondPlayerResult(square) = true
    if (player == 1) checkIfWin(firstPlayerResult) else checkIfWin(secondPlayerResult)
  }
/**
  * Check If Somebody Win 
  *
  * @param data
  * @return Boolean
  */
  def checkIfWin(data:scala.collection.mutable.Map[Int, Boolean]):Boolean = {
    val row:Boolean = if(data(1) == true && data(2) == true && data(3) == true || data(4) == true && data(5) == true && data(6) == true ||data(7) == true && data(8) == true && data(9) == true  ) true else false 
    val column:Boolean = if(data(1) == true && data(4) == true && data(7) == true || data(2) == true && data(5) == true && data(8) == true ||data(3) == true && data(6) == true && data(9) == true  ) true else false 
    val slanting:Boolean = if(data(1) == true && data(5) == true && data(9) == true || data(3) == true && data(5) == true && data(7) == true ) true else false 
   
    if (row || column || slanting) if (player == 1)winMessage(1) else winMessage(2) else if (pressedButtonCounter == 9) winMessage(3)
   
    return if (row || column || slanting) true else false ;
  }
  /**
    * Open Win Frame To Know Who Is Winner 
    *
    * @param player
    */
  def winMessage(player:Int):Unit={
      val message:String = player match {
      case  1 => "Player One Win "
      case  2 => "Player Two Win"
      case  3 => "No One Win"
      }
        winFrame.contents =  new GridPanel(2, 1) {
          contents +=  new Label(message)
          contents +=  Button("play agin")(restartGame)
        }
          winFrame.centerOnScreen
  
    mainFrame.visible = false ;
    winFrame.open();
  }
  /**
    * Empty All Button And Close Win Frame And Empty First And Second Player Result Map And Open Main Frame Agin 
    */
  def restartGame():Unit = { 
    for (data <- 1 to 9){
      firstPlayerResult(data) = false
      secondPlayerResult(data) = false
    }
    player = 1 ;
     winFrame.close();
    mainFrame.visible = true;
    emptyButton();
    
  }
  /**
    * Check The Square Position And Write On Button  
    *
    * @param player
    * @param square
    */
  def gameText(player: Int, square: Int): Unit = square match {
    case 1 =>if(player == 1) button1.text = "X" else button1.text = "O" 
    case 2 =>if(player == 1) button2.text = "X" else button2.text = "O" 
    case 3 =>if(player == 1) button3.text = "X" else button3.text = "O" 
    case 4 =>if(player == 1) button4.text = "X" else button4.text = "O" 
    case 5 =>if(player == 1) button5.text = "X" else button5.text = "O" 
    case 6 =>if(player == 1) button6.text = "X" else button6.text = "O" 
    case 7 =>if(player == 1) button7.text = "X" else button7.text = "O" 
    case 8 =>if(player == 1) button8.text = "X" else button8.text = "O" 
    case 9 =>if(player == 1) button9.text = "X" else button9.text = "O" 
  }
  /**
    * Empty All Button Text 
    */
  def emptyButton():Unit = {
      button1.text = "";
      button2.text = "";
      button3.text = "";
      button4.text = "";
      button5.text = "";
      button6.text = "";
      button7.text = "";
      button8.text = "";
      button9.text = "";
  }
    /**
      * Control Role Play And Return The Current Player 
      *
      * @return Int
      */
  def getPlayer(): Int = {
    if (this.player == 1) {
      this.player = 2;
      return 1
    } else {
      this.player = 1;
      return 2
    };
  }

/////////////////////////// Functions  ///////////////////////////


}
