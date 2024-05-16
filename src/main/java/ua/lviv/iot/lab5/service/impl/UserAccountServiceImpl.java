package ua.lviv.iot.lab5.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.lviv.iot.lab5.domain.UserAccount;
import ua.lviv.iot.lab5.exception.ReviewOfEstablishmentExistForUserAccountException;
import ua.lviv.iot.lab5.exception.UserAccountNotFoundException;
import ua.lviv.iot.lab5.repository.UserAccountRepository;
import ua.lviv.iot.lab5.service.UserAccountService;

import java.util.List;

@Service
public class UserAccountServiceImpl implements UserAccountService {
    private final UserAccountRepository userAccountRepository;

    @Autowired
    public UserAccountServiceImpl(UserAccountRepository userAccountRepository) {
        this.userAccountRepository = userAccountRepository;
    }

    @Override
    public List<UserAccount> findAll() {
        return userAccountRepository.findAll();
    }

    @Override
    public UserAccount findById(String nickname) {
        return userAccountRepository.findById(nickname)
                .orElseThrow(() -> new UserAccountNotFoundException(nickname));
    }

    @Override
    public UserAccount create(UserAccount userAccount) {
        userAccountRepository.save(userAccount);
        return userAccount;
    }

    @Override
    public void update(String nickname, UserAccount uUserAccount) {
        UserAccount userAccount = userAccountRepository.findById(nickname)
                .orElseThrow(() -> new UserAccountNotFoundException(nickname));
        userAccount.setName(uUserAccount.getName());
        userAccount.setSurname(uUserAccount.getSurname());
    }

    @Override
    public void delete(String nickname) {
        UserAccount userAccount = userAccountRepository.findById(nickname)
                .orElseThrow(() -> new UserAccountNotFoundException(nickname));
        if (!userAccount.getReviewOfEstablishments().isEmpty())
            throw new ReviewOfEstablishmentExistForUserAccountException(nickname);
        userAccountRepository.delete(userAccount);
    }
}
