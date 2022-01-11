package com.ds.nofication.Listeners;

import com.ds.nofication.Models.Backend.MedicineDkDTO;

public interface MedicineDkListener {
    /**
     * Method that will be called on response
     * @param medicineDkDTO Medicine
     */
    public void update(MedicineDkDTO medicineDkDTO);

    /**
     * Method that will be called if error has occurred
     * @param errorMessage errorMessage
     */
    public void errorUpdate(String errorMessage);
}
