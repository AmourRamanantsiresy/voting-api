package org.ambohipotsy.votingapp.model.utilities;

import lombok.Getter;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;

@Getter
public class Page {
    private final int page;

    public Page(Integer page) {
        if (page == null) {
            this.page = 1;
        } else if (page < 1) {
            throw new BadRequestException("Page must be >= 1");
        } else {
            this.page = page;
        }
    }
}
