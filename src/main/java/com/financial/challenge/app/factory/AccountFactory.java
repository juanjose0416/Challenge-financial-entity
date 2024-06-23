package com.financial.challenge.app.factory;

import com.financial.challenge.domain.model.Account;
import com.financial.challenge.domain.model.Client;

public interface AccountFactory {
  public Account create(Boolean isGMF, Client client);
}
