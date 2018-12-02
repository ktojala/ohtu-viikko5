package ohtu;

public class TennisGame {
    
    private final int BASIC_SCORES_LIMIT = 3;
    private final int STATUS_ADVANTAGE = 1;
    
    private int player1Score = 0;
    private int player2Score = 0;
    private String player1Name;
    private String player2Name;

    public TennisGame(String player1Name, String player2Name) {
        this.player1Name = player1Name;
        this.player2Name = player2Name;
    }

    public void wonPoint(String playerName) {
        if (playerName.equals(player1Name)) {
            player1Score += 1;
        } else {
            player2Score += 1;
        }
    }

    public String getScore() {
        if (player1Score == player2Score) {
            return getEvenScore();
        } else if (player1Score > BASIC_SCORES_LIMIT || player2Score > BASIC_SCORES_LIMIT) {
            return getAdvantageOrWin();
        } else {
            return getScoreText(player1Score) + "-" + getScoreText(player2Score);
        }
    }
    
    private String getEvenScore() {
        if (player1Score <= BASIC_SCORES_LIMIT ) {
            return getScoreText(player1Score) + "-All";
        }
        return getScoreText(player1Score);        
    }
    
    private String getScoreText(int point) {
        switch (point) {
            case 0:
                return "Love";
            case 1:
                return "Fifteen";
            case 2:
                return "Thirty";
            case 3:
                return "Forty";
            default:
                return "Deuce";
        }
    }
    
    private String getAdvantageOrWin() {
        String text = "";
        int difference = Math.abs(player1Score - player2Score);
        if (difference == STATUS_ADVANTAGE) {
            text += "Advantage ";
        } else {
            text += "Win for ";
        }
        text += getPlayerWithBetterScore();
        return text;
    }

    private String getPlayerWithBetterScore() {
        if (player1Score > player2Score) {
            return player1Name;
        } 
        return player2Name;
    }
    
}

