package idv.tha101.extractp.web.service.impl;

import java.lang.reflect.Field;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import idv.tha101.extractp.web.dao.EventRepository;
import idv.tha101.extractp.web.pojo.EventVO;
import idv.tha101.extractp.web.service.EventService;




@Service
public class EventServiceImpl implements EventService {
	@Autowired
	private EventRepository eventRepositry;

	@Override
	public List<EventVO> findAll() {
		return eventRepositry.findAll();
	}

	@Override
	public EventVO findById(Integer id) {
		return eventRepositry.findById(id).orElseThrow();
	}

	@Override
	public EventVO saveOrUpdate(EventVO vo) {
		if (vo.getId() != null) {
			Optional<EventVO> optionalVO = eventRepositry.findById(vo.getId());
			if (optionalVO.isPresent()) {
				EventVO existingVO = optionalVO.get();

				Class<?> voClass = EventVO.class;
				Field[] fields = voClass.getDeclaredFields();

				for (Field field : fields) {
					field.setAccessible(true);
					try {
						Object updatedValue = field.get(vo);
						if (updatedValue != null) {
							field.set(existingVO, updatedValue);
						}
					} catch (IllegalAccessException e) {
						e.printStackTrace();
					}
				}

				return eventRepositry.save(existingVO);
			} else {
				return null;
			}
		} else {
			return eventRepositry.save(vo);
		}
	}

	@Override
	public void deleteById(Integer id) {
		eventRepositry.deleteById(id);
	}

	}
