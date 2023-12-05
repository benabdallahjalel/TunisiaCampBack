package esprit.tunisiacamp.services;

import esprit.tunisiacamp.entities.User;
import esprit.tunisiacamp.entities.forum.Comment;

import java.util.List;

public interface IserviceCommentaire {

    void ajoutercommentare(Comment m, long idPost, long idUser);

    public List<Comment> getComments();

    void supprimercommentaire(long idcomment);

    public void modifierCommentaire(Comment commentaire);
    boolean contientMotInterdit(String ContentPost);

}
