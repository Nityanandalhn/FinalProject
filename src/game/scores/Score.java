package game.scores;
import java.io.*;

public class Score {
    /**
     * This method will save the highest score on the first line, and the last
     * score on the second line. If the current score is the highest score, it
     * will store the same score on both lines.
     * @param score int current score
     */
    public static void saveScores(int score, int[] scores)
    {
        try (PrintWriter pw = new PrintWriter("scores.dat"))
        {
            pw.println(Math.max(scores[0], score));
            pw.println(score);

        } catch (IOException ignored) {
        }
    }

    /**
     * This method returns the highest and the last score
     * @return int[] [0] = highest [1] = last
     */
    public static int[] loadScores()
    {
        int[] scores = {-1, -1};

        try (BufferedReader br = new BufferedReader(
                new FileReader("scores.dat")))
        {
            String line;
            int ln = 0;
            while ((line = br.readLine()) != null)
            {
                scores[ln++] = Integer.parseInt(line);
            }
        } catch (IOException e) {
        }

        return scores;
    }
}