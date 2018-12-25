package model;


import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

@XmlRootElement
public class GameStatus {
    private int _moves;
    private int _timer;

    public int get_moves() {
        return _moves;
    }
    @XmlElement
    public void set_moves(int _moves) {
        this._moves = _moves;
    }

    public int get_timer() {
        return _timer;
    }
    @XmlElement

    public void set_timer(int _timer) {
        this._timer = _timer;
    }

}
