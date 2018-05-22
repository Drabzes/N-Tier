package com.example.giel.parking.services.implementations;


import static java.util.Objects.nonNull;
import static java.util.stream.Collectors.summingDouble;
import static java.util.stream.Collectors.toList;
import static org.springframework.util.CollectionUtils.isEmpty;


import com.example.giel.parking.models.EntryHistory;
import com.example.giel.parking.models.UserInfo;
import com.example.giel.parking.repositories.dao.EntryHistoryRepository;
import com.example.giel.parking.repositories.dao.UserInfoRepository;
import com.example.giel.parking.services.interfaces.IBalanceService;
import java.time.LocalDate;
import java.time.Month;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

@Service
public class BalanceServiceImpl implements IBalanceService {


  @Autowired
  private EntryHistoryRepository entryHistoryRepository;

  @Autowired
  private UserInfoRepository userInfoRepository;

  @Override
  public Double getCurrentCustomerBalanceByUuid(String uuid) {

    UserInfo userInfo = userInfoRepository.getUserInfoByUuid(uuid);

    int currentYear = LocalDate.now().getYear();
    Month currentMonth = LocalDate.now().getMonth();

    return getBalanceByMonth(userInfo.getId(), currentMonth, currentYear);
  }

  @Override
  public Double getPreviousCustomerBalanceById(Long userInfoId) {

    int currentYear = LocalDate.now().getYear();
    Month currentMonth = LocalDate.now().minusMonths(1).getMonth();

    return getBalanceByMonth(userInfoId, currentMonth, currentYear);
  }

  private Double getBalanceByMonth(Long userId, Month month, int year) {

    List<EntryHistory> entryHistoryList = entryHistoryRepository
        .findAllByUserInfoIdAndCustomerInfoIdOrCustomerInfoId(userId, null, userId);

    if (isEmpty(entryHistoryList)) {
      return Double.valueOf(0);
    }

    List<EntryHistory> filteredList = entryHistoryList.stream().filter(
        history -> nonNull(history.getLeavingTime())
            && history.getLeavingTime().getMonth() == month
            && history.getLeavingTime().getYear() == year)
        .collect(toList());

    return filteredList.stream().collect(summingDouble(EntryHistory::getEntryCost));
  }

}
