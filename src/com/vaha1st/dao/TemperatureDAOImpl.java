package com.vaha1st.dao;

import com.vaha1st.entity.TempConversion;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * {@code TemperatureDAOImpl} имплементация интерфейса DAO для для взаимодействия сервиса и БД.
 *
 * @author Руслан Вахитов
 * @version 1.00 4 May 2020
 */
@Repository
public class TemperatureDAOImpl implements TemperatureDAO {

    // Подключение фафбрики hibernate
    @Autowired
    private SessionFactory sessionFactory;

    // Получение списка конвертаций из таблицы БД "converting_history" путем запроса hql
    @Override
    public List<TempConversion> getTempEntity() {

        Session currentSession = sessionFactory.getCurrentSession();

        Query<TempConversion> query = currentSession.createQuery("from TempConversion", TempConversion.class);

        List<TempConversion> conversions = query.getResultList();

        return conversions;
    }

    // Сохранение конвертации в БД
    @Override
    public void saveConversion(TempConversion tempConversion) {

        Session currentSession = sessionFactory.getCurrentSession();

        currentSession.save("conversion", tempConversion);
    }

    // Удаление записи конвертации из таблицы по полченному из модели id
    @Override
    public void deleteInput(int id) {
        Session currentSession = sessionFactory.getCurrentSession();

        Query<TempConversion> theQuery = currentSession.createQuery(
                "delete from TempConversion where id=:theConversionId");

        theQuery.setParameter("theConversionId", id);

        theQuery.executeUpdate();
    }

    // Очистка таблицы "converting_history" путем запроса hql
    @Override
    public void clearHistory() {
        Session currentSession = sessionFactory.getCurrentSession();
        Query<TempConversion> theQuery = currentSession.createQuery(
                "delete from TempConversion");
        theQuery.executeUpdate();

        // сброс id
        currentSession.createSQLQuery(
                "ALTER TABLE converting_history AUTO_INCREMENT=1").executeUpdate();
    }
}
