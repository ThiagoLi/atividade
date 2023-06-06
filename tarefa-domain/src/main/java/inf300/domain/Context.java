package inf300.domain;


/**
 *
 * @author esoft
 * @param <S>
 */
public interface Context<S extends State> {
    
    State getStatus();
    void setStatus(S state);
    
}
