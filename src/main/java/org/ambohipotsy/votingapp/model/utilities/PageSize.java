package org.ambohipotsy.votingapp.model.utilities;

import lombok.Getter;
import org.ambohipotsy.votingapp.model.exceptions.BadRequestException;

@Getter
public class PageSize {
  private final int MAX_PAGE_SIZE = 500;
  private final int DEFAULT_PAGE_SIZE = 10;
  private final int pageSize;

  public PageSize(Integer pageSize) {
    if (pageSize == null) {
      this.pageSize = DEFAULT_PAGE_SIZE;
    } else if (pageSize > MAX_PAGE_SIZE) {
      throw new BadRequestException("Page size must be <= " + MAX_PAGE_SIZE);
    } else {
      this.pageSize = pageSize;
    }
  }
}
