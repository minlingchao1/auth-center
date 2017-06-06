package com.lingchaomin.auth.cas.registry;

import org.jasig.cas.ticket.Ticket;
import org.jasig.cas.ticket.TicketGrantingTicket;
import org.jasig.cas.ticket.registry.AbstractDistributedTicketRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.Collection;

public class RedisTicketRegistry extends AbstractDistributedTicketRegistry {

    private static final Logger log= LoggerFactory.getLogger(RedisTicketRegistry.class);

    private  int st_time;  //ST最大空闲时间
    private  int tgt_time; //TGT最大空闲时间
    private RedisManager redisManager;


//    public RedisTicketRegistry( int st_time, int tgt_time,RedisManager redisManager) {
//        this.st_time = st_time;
//        this.tgt_time = tgt_time;
//        this.redisManager=redisManager;
//    }

    public void addTicket(Ticket ticket) {

        int seconds = 0;

        String key = ticket.getId() ;

        if(ticket instanceof TicketGrantingTicket){
            //key = ((TicketGrantingTicket)ticket).getAuthentication().getPrincipal().getId();
            seconds = tgt_time/1000;
        }else{
            seconds = st_time/1000;
        }


        ByteArrayOutputStream bos = new ByteArrayOutputStream();
        ObjectOutputStream oos = null;
        try{
            oos = new ObjectOutputStream(bos);
            oos.writeObject(ticket);

            log.info("adding ticket:{} to redis success~~~~~~~~~",ticket);

        }catch(Exception e){
            log.error("adding ticket to redis error.");
        }finally{
            try{
                if(null!=oos) oos.close();
            }catch(Exception e){
                log.error("oos closing error when adding ticket to redis.");
            }
        }

        redisManager.set(key.getBytes(), bos.toByteArray());
        redisManager.expire(key.getBytes(),seconds);
    }

    public Ticket getTicket(final String ticketId) {

        log.info("get ticket:{}",ticketId);
        return getProxiedTicketInstance(getRawTicket(ticketId));
    }


    private Ticket getRawTicket(final String ticketId) {

        log.info("ticketId:{}",ticketId);


        if(null == ticketId) return null;


        Ticket ticket = null;

        if(redisManager.get(ticketId.getBytes())==null){
            return null;
        }
        ByteArrayInputStream bais = new ByteArrayInputStream(redisManager.get(ticketId.getBytes()));
        ObjectInputStream ois = null;

        try{
            ois = new ObjectInputStream(bais);
            ticket = (Ticket)ois.readObject();
            log.info("getRawTicket:{}",ticket);
        }catch(Exception e){
            log.error("getting ticket to redis error.");
        }finally{
            try{
                if(null!=ois)  ois.close();
            }catch(Exception e){
                log.error("ois closing error when getting ticket to redis.");
            }
        }

        return ticket;
    }



    public boolean deleteTicket(final String ticketId) {

        if (ticketId == null) {
            return false;
        }

        redisManager.del(ticketId.getBytes());

        return true;
    }

    public Collection<Ticket> getTickets() {
        logger.info("gettickets not supported~~~~");

        throw new UnsupportedOperationException("GetTickets not supported.");
    }

    public RedisManager getRedisManager() {
        return redisManager;
    }

    public void setRedisManager(RedisManager redisManager) {
        this.redisManager = redisManager;
    }

    public int getSt_time() {
        return st_time;
    }

    public void setSt_time(int st_time) {
        this.st_time = st_time;
    }

    public int getTgt_time() {
        return tgt_time;
    }

    public void setTgt_time(int tgt_time) {
        this.tgt_time = tgt_time;
    }

    protected boolean needsCallback() {
        return false;
    }

    protected void updateTicket(final Ticket ticket) {
        addTicket(ticket);
    }

}
