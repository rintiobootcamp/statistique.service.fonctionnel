package com.bootcamp.services;

import com.bootcamp.client.CommentaireClient;
import com.bootcamp.client.DebatClient;
import com.bootcamp.client.LikeClient;
import com.bootcamp.entities.Commentaire;
import com.bootcamp.entities.Debat;
import com.bootcamp.entities.LikeTable;

import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.io.IOException;

import java.text.DecimalFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;

import java.util.List;

/**
 * Created by Bignon reviewed by Moh.
 */
@Component
public class StatistiqueService {

    DebatClient debatClient;
    CommentaireClient commentaireClient;
    LikeClient likeClient;

    @PostConstruct

    public void init() {

        debatClient = new DebatClient();
        commentaireClient = new CommentaireClient();
        likeClient = new LikeClient();
    }

    public Stat getStatistique(String entity, int entityId, String startDate, String endDate) throws IOException {

        List<Commentaire> commentaires = commentaireClient.getAllCommentByAllEntity(entity, startDate, endDate);
        List<LikeTable> likes = likeClient.getAllLikeOrUnlikeByEntity(entity, startDate, endDate);
        List<Debat> debats = debatClient.getByEntityType(entity, startDate, endDate);
        //Pour debat
        double nbreDebat = 0;
        double nbreTotalDebat = 0;
        //Pour Commentaire
        double nbreComment = 0;
        double nbreTotalComment = 0;

        //Pour Like/Unlike
        double nbreLike = 0;
        double nbreTotalLike = 0;
        double nbreUnLike = 0;
        double nbreTotalUnLike = 0;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4); //arrondi à 4 chiffres apres la virgules
        df.setMinimumFractionDigits(2);

        for (Debat debat : debats) {
            if (debat.getEntityId() == entityId) {
                nbreDebat++;
            }
        }

        for (Commentaire commentaire : commentaires) {

            if (commentaire.getEntityId() == entityId) {
                nbreComment++;
            }

        }

        for (LikeTable like : likes) {
            if (like.isLikeType()) {
                nbreTotalLike++;
                if (like.getEntityId() == entityId) {
                    nbreLike++;
                }
            } else if (!like.isLikeType()) {
                nbreTotalUnLike++;
                if (like.getEntityId() == entityId) {
                    nbreUnLike++;
                }
            }
        }
        //Double.parseDouble(df.format(
        nbreTotalComment = commentaires.size();
        nbreTotalDebat = debats.size();

        Stat stat = new Stat();
        stat.setNbreComment(nbreComment);

        if (nbreTotalComment == 0) {
            stat.setTauxComment(0);
        } else {
            stat.setTauxComment(nbreComment / nbreTotalComment);
        }

        stat.setNbreDebat(nbreDebat);
        if (nbreTotalDebat == 0) {
            stat.setTauxDebat(0);
        } else {
            stat.setTauxDebat((nbreDebat / nbreTotalDebat));
        }

        stat.setNbreLike(nbreLike);
        if (nbreTotalLike == 0) {
            stat.setTauxLike(0);
        } else {

            stat.setTauxLike((nbreLike / nbreTotalLike));
        }
        stat.setNbreUnLike(nbreUnLike);
        if (nbreTotalUnLike == 0) {
            stat.setTauxUnLike(0);
        } else {

            stat.setTauxUnLike((nbreUnLike / nbreTotalUnLike));
        }
        stat.setNbreTotalComment(nbreTotalComment);
        stat.setNbreTotalDebat(nbreTotalDebat);
        stat.setNbreTotalLike(nbreTotalLike);
        stat.setNbreTotalUnLike(nbreTotalUnLike);

        return stat;
    }

    public List<Stat> getStatistiqueByPas(String entity, int entityId, String startDate, String endDate, int pas) throws IOException, ParseException {
        List<Stat> stats = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < 10; i = i + pas) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(formatter.parse(startDate));
            calendar1.add(Calendar.DATE, pas);
            String dateDebut = formatter.format(calendar1.getTime());

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(formatter.parse(endDate));
            calendar2.add(Calendar.DATE, pas);
            String dateFin = formatter.format(calendar2.getTime());

            Stat stat = this.getStatistique(entity, entityId, dateDebut, dateFin);
            stats.add(stat);
        }

        return stats;

    }

    public StatGlobal getStatistiqueAll(String entity, String startDate, String endDate) throws IOException {

        List<Commentaire> commentaires = commentaireClient.getAllCommentByAllEntity(entity, startDate, endDate);
        List<LikeTable> likes = likeClient.getAllLikeOrUnlikeByEntity(entity, startDate, endDate);
        List<Debat> debats = debatClient.getByEntityType(entity, startDate, endDate);
        //Pour debat
        double nbreTotalDebat = 0;
        //Pour Commentaire
        double nbreTotalComment = 0;

        //Pour Like/Unlike
        double nbreTotalLike = 0;
        double nbreTotalUnLike = 0;

        DecimalFormat df = new DecimalFormat();
        df.setMaximumFractionDigits(4); //arrondi à 4 chiffres apres la virgules
        df.setMinimumFractionDigits(2);

        for (LikeTable like : likes) {
            if (like.isLikeType()) {
                nbreTotalLike++;

            } else if (!like.isLikeType()) {
                nbreTotalUnLike++;

            }
        }
        //Double.parseDouble(df.format(
        nbreTotalComment = commentaires.size();
        nbreTotalDebat = debats.size();

        StatGlobal stat = new StatGlobal();

        stat.setNbreTotalComment(nbreTotalComment);
        stat.setNbreTotalDebat(nbreTotalDebat);
        stat.setNbreTotalLike(nbreTotalLike);
        stat.setNbreTotalUnLike(nbreTotalUnLike);

        return stat;
    }

    public List<StatGlobal> getStatistiqueAllByPas(String entity, String startDate, String endDate, int pas) throws IOException, ParseException {
        List<StatGlobal> stats = new ArrayList<>();
        SimpleDateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");

        for (int i = 0; i < 10; i = i + pas) {
            Calendar calendar1 = Calendar.getInstance();
            calendar1.setTime(formatter.parse(startDate));
            calendar1.add(Calendar.DATE, pas);
            String dateDebut = formatter.format(calendar1.getTime());

            Calendar calendar2 = Calendar.getInstance();
            calendar2.setTime(formatter.parse(endDate));
            calendar2.add(Calendar.DATE, pas);
            String dateFin = formatter.format(calendar2.getTime());

            StatGlobal stat = this.getStatistiqueAll(entity, dateDebut, dateFin);
            stats.add(stat);
        }

        return stats;

    }

}
